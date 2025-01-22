package com.fsse2401.project.service.impl;

import com.fsse2401.project.data.cart.entity.CartItemEntity;

import com.fsse2401.project.data.product.entity.ProductEntity;
import com.fsse2401.project.data.transaction.entity.TransactionEntity;
import com.fsse2401.project.data.transaction.entity.TransactionProductEntity;
import com.fsse2401.project.exception.exceptionList.DataNotFoundException;
import com.fsse2401.project.exception.exceptionList.StockQuantityNotEnoughException;
import com.fsse2401.project.repository.TransactionProductRepository;
import com.fsse2401.project.service.CartItemService;
import com.fsse2401.project.service.ProductService;
import com.fsse2401.project.service.TransactionProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionProductServiceImpl implements TransactionProductService {
    @Autowired
    private final TransactionProductRepository transactionProductRepository;
    @Autowired
    private final CartItemService cartItemService;
    @Autowired
    private final ProductService productService;

    public TransactionProductServiceImpl(TransactionProductRepository transactionProductRepository, CartItemService cartItemService, ProductService productService) {
        this.transactionProductRepository = transactionProductRepository;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    public TransactionProductEntity postTransactionProduct(CartItemEntity cartItem, TransactionEntity transactionEntity) {

        if (cartItem.getProduct().getStock() < cartItem.getQuantity()){
            throw new StockQuantityNotEnoughException(cartItem.getProduct().getStock(), cartItem.getQuantity());
        }

        TransactionProductEntity transactionProduct = new TransactionProductEntity(cartItem, transactionEntity);
        //reminder: tobe reduce in update transaction API
        //productService.updateProductEntity(cartItem.getProduct(), cartItem.getQuantity());
        return transactionProductRepository.save(transactionProduct);
    }

    @Override
    public List<TransactionProductEntity> postAllTransactionProduct(TransactionEntity transactionEntity){
        List<TransactionProductEntity> transactionProductEntityList = new ArrayList<>();
        for (CartItemEntity cartItem: cartItemService.getAllCartItemByUser(transactionEntity.getUser())){
            transactionProductEntityList.add(postTransactionProduct(cartItem, transactionEntity));
        }
        return transactionProductEntityList;
    }
}
