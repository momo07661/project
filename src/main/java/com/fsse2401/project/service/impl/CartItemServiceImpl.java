package com.fsse2401.project.service.impl;

import com.fsse2401.project.data.cart.domainObject.response.ResponseCartData;
import com.fsse2401.project.data.cart.entity.CartItemEntity;
import com.fsse2401.project.data.product.entity.ProductEntity;
import com.fsse2401.project.data.user.domainObject.FireBaseUserData;
import com.fsse2401.project.data.user.entity.UserEntity;
import com.fsse2401.project.exception.exceptionList.CartItemNotFoundException;
import com.fsse2401.project.exception.exceptionList.MissingRequiredDataException;
import com.fsse2401.project.repository.CartItemRepository;
import com.fsse2401.project.service.CartItemService;
import com.fsse2401.project.service.ProductService;
import com.fsse2401.project.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private final UserService userService;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(UserService userService, ProductService productService, CartItemRepository cartItemRepository) {
        this.userService = userService;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartItemEntity getCartItem(FireBaseUserData data, Integer pid){
        ProductEntity productEntity = productService.getProductEntityByPid(pid);
        UserEntity userEntity = userService.getUserEntityByFirebaseUserData(data);
        return cartItemRepository.findByPidAndUid(productEntity.getPid(), userEntity.getUid()).orElseThrow(CartItemNotFoundException::new);
    }

    @Override
    public ResponseCartData putCartItem(FireBaseUserData data, Integer pid, Integer quantity){
        if (data == null || pid == null || quantity <= 0 || quantity == null){
            throw new MissingRequiredDataException();
        }
        ProductEntity productEntity = productService.getProductEntityByPid(pid);
        UserEntity userEntity = userService.getUserEntityByFirebaseUserData(data);

        Optional<CartItemEntity> optionalCattItemEntity = cartItemRepository.findByPidAndUid(productEntity.getPid(), userEntity.getUid());
        if (optionalCattItemEntity.isEmpty()){
            //check is valid quantity
            productService.isValidQuantity(productEntity, quantity);
            return new ResponseCartData(cartItemRepository.save(new CartItemEntity(productEntity, userEntity, quantity)));
        }else {
            //check is valid new quantity
            productService.isValidQuantity(productEntity, optionalCattItemEntity.get().getQuantity() + quantity);
            optionalCattItemEntity.get().setQuantity(optionalCattItemEntity.get().getQuantity() + quantity);
            return new ResponseCartData(cartItemRepository.save(optionalCattItemEntity.get()));
        }
    }

    @Override
    public List<CartItemEntity> getAllCartItemByUser(UserEntity user){

        List<CartItemEntity> cartItemEntityList = new ArrayList<>();

        for (CartItemEntity entity: cartItemRepository.findAllByUid(user.getUid())){
            cartItemEntityList.add(entity);
        }

        if (cartItemEntityList.isEmpty()){
            throw new CartItemNotFoundException();
        }

        return cartItemEntityList;
    }

    @Override
    public List<ResponseCartData> getCartItem(FireBaseUserData data){

        List<ResponseCartData> cartItemDataList = new ArrayList<>();

        for (CartItemEntity entity: cartItemRepository.findAllByUid(userService.getUserEntityByFirebaseUserData(data).getUid())){
            cartItemDataList.add(new ResponseCartData(entity));
        }
//can be an empty list
/*        if (cartItemDataList.isEmpty()){
            throw new CartItemNotFoundException();
        }*/

        return cartItemDataList;
    }

    @Override
    public ResponseCartData getCartItemByPid(FireBaseUserData data, Integer pid){
        if (data == null || pid == null){
            throw new MissingRequiredDataException();
        }
        Optional<CartItemEntity> temCartItem = cartItemRepository.findByPidAndUid(pid, userService.getUserEntityByFirebaseUserData(data).getUid());

        if (temCartItem.isEmpty()){
            return null;
        }
        return new ResponseCartData(temCartItem.orElseThrow(CartItemNotFoundException::new));
    }

    @Override
    public ResponseCartData updateCartQuantity(FireBaseUserData data, Integer pid, Integer quantity){
        if (data == null || pid == null || quantity <= 0 || quantity == null){
            throw new MissingRequiredDataException();
        }

        CartItemEntity cartItemEntity = getCartItem(data, pid);

        //check is valid quantity
        productService.isValidQuantity(cartItemEntity.getProduct(), quantity);

        cartItemEntity.setQuantity(quantity);
        return new ResponseCartData(cartItemRepository.save(cartItemEntity));
    }

    @Override
    public ResponseCartData deleteCartItem(FireBaseUserData data, Integer pid){
        if (pid == null || data == null){
            throw new MissingRequiredDataException();
        }

        CartItemEntity cartItemEntity = getCartItem(data, pid);

        ResponseCartData cartData = new ResponseCartData(cartItemEntity);
        cartItemRepository.delete(cartItemEntity);
        return cartData;
    }

    @Transactional
    @Override
    public boolean deleteAllCartItem(FireBaseUserData data){
        UserEntity user = userService.getUserEntityByFirebaseUserData(data);
        cartItemRepository.deleteAllByUser(user);
        return cartItemRepository.countByUser(user) == 0;
    }


}
