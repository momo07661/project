package com.fsse2401.project.service;

import com.fsse2401.project.data.cart.domainObject.response.ResponseCartData;
import com.fsse2401.project.data.cart.entity.CartItemEntity;
import com.fsse2401.project.data.user.domainObject.FireBaseUserData;
import com.fsse2401.project.data.user.entity.UserEntity;

import java.util.List;

public interface CartItemService {

    CartItemEntity getCartItem(FireBaseUserData data, Integer pid);

    ResponseCartData putCartItem(FireBaseUserData data, Integer pid, Integer quantity);

    List<CartItemEntity> getAllCartItemByUser(UserEntity user);

    List<ResponseCartData> getCartItem(FireBaseUserData data);

    ResponseCartData getCartItemByPid(FireBaseUserData data, Integer pid);

    ResponseCartData updateCartQuantity(FireBaseUserData data, Integer pid, Integer quantity);


    ResponseCartData deleteCartItem(FireBaseUserData data, Integer pid);

    boolean deleteAllCartItem(FireBaseUserData data);
}
