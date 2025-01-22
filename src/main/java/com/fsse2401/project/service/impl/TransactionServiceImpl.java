package com.fsse2401.project.service.impl;

import com.fsse2401.project.data.transaction.domainObject.ResponseTransactionData;
import com.fsse2401.project.data.transaction.entity.TransactionEntity;
import com.fsse2401.project.data.transaction.entity.TransactionProductEntity;
import com.fsse2401.project.data.user.domainObject.FireBaseUserData;
import com.fsse2401.project.exception.exceptionList.DataNotFoundException;
import com.fsse2401.project.exception.exceptionList.MissingRequiredDataException;
import com.fsse2401.project.repository.TransactionRepository;
import com.fsse2401.project.service.*;
import com.fsse2401.project.util.TransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private final TransactionProductService transactionProductService;
    @Autowired
    private final TransactionRepository transactionRepository;
    @Autowired
    private final UserService userService;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final CartItemService cartItemService;
    public TransactionServiceImpl(TransactionProductService transactionProductService, TransactionRepository transactionRepository, UserService userService, ProductService productService, CartItemService cartItemService) {
        this.transactionProductService = transactionProductService;
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.productService = productService;
        this.cartItemService = cartItemService;
    }

    public TransactionEntity postTemTransaction(FireBaseUserData data){
        return transactionRepository.save(new TransactionEntity(userService.getUserEntityByFirebaseUserData(data)));
    }

    @Override
    public ResponseTransactionData postTransaction(FireBaseUserData data){
        TransactionEntity transactionEntity = postTemTransaction(data);
        //set total & save all cart item to server
        List<TransactionProductEntity> items = transactionProductService.postAllTransactionProduct(transactionEntity);
        transactionEntity.setItems(items);

        return new ResponseTransactionData(transactionRepository.save(transactionEntity));
    }

    @Override
    public ResponseTransactionData getTransactionById(FireBaseUserData data, Integer tid){
        if (tid == null){
            throw new MissingRequiredDataException();
        }
        return new ResponseTransactionData(transactionRepository.findByTidAndUid(tid, userService.getUserEntityByFirebaseUserData(data).getUid()).orElseThrow(DataNotFoundException::new));
    }

    @Override
    public ResponseTransactionData updateTransactionStatus(FireBaseUserData data, Integer tid){
        if (tid == null){
            throw new MissingRequiredDataException();
        }

        TransactionEntity transactionEntity = transactionRepository.findByTidAndUid(tid, userService.getUserEntityByFirebaseUserData(data).getUid()).orElseThrow(DataNotFoundException::new);
        for (TransactionProductEntity transactionProductEntity: transactionEntity.getItems()){
            productService.updateProductEntity(productService.getProductEntityByPid(transactionProductEntity.getPid()), transactionProductEntity.getQuantity());
        }

        if (transactionEntity.getStatus() != TransactionState.PREPARE){
            throw new DataNotFoundException();
        }

        transactionEntity.setStatus(TransactionState.PROCESSING);
        return new ResponseTransactionData(transactionRepository.save(transactionEntity));
    }

    @Override
    public ResponseTransactionData finishTransactionStatus(FireBaseUserData data, Integer tid){
        if (tid == null){
            throw new MissingRequiredDataException();
        }

        TransactionEntity transactionEntity = transactionRepository.findByTidAndUid(tid, userService.getUserEntityByFirebaseUserData(data).getUid()).orElseThrow(DataNotFoundException::new);

        if (transactionEntity.getStatus() != TransactionState.PROCESSING){
            System.out.println("error in ln:92");
            throw new DataNotFoundException();
        }

        if (!cartItemService.deleteAllCartItem(data)){
            System.out.println("error in ln:87");
            throw new DataNotFoundException();
        }

        transactionEntity.setStatus(TransactionState.SUCCESS);

        return new ResponseTransactionData(transactionRepository.save(transactionEntity));
    }

    @Override
    public void updateTransactionSessionId(FireBaseUserData data, Integer tid, String sessionId){
        TransactionEntity transactionEntity = transactionRepository.findByTidAndUid(tid, userService.getUserEntityByFirebaseUserData(data).getUid()).orElseThrow(DataNotFoundException::new);
        transactionEntity.setStripeSessionId(sessionId);
        transactionRepository.save(transactionEntity);
    }
}
