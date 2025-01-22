package com.fsse2401.project.data.cart.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2401.project.data.cart.domainObject.response.ResponseCartData;

public class GetQuantityResponseCartDto {
    @JsonProperty("cart_quantity")
    private Integer cartQuantity;

    public GetQuantityResponseCartDto(ResponseCartData data) {
        this.cartQuantity = data.getQuantity();
    }

    public GetQuantityResponseCartDto(Integer data) {
        this.cartQuantity = data;
    }

    public Integer getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(Integer cartQuantity) {
        this.cartQuantity = cartQuantity;
    }
}
