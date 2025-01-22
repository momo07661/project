package com.fsse2401.project.data.cart.domainObject.response;

import com.fsse2401.project.data.cart.entity.CartItemEntity;
import com.fsse2401.project.data.product.domainObject.response.ResponseProductData;
import com.fsse2401.project.data.product.entity.ProductEntity;
import com.fsse2401.project.data.user.domainObject.ResponseUserData;
import com.fsse2401.project.data.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ResponseCartData {
    private Integer cid;
    private ResponseProductData product;
    private ResponseUserData user;
    private Integer quantity;

    public ResponseCartData(CartItemEntity entity) {
        this.cid = entity.getCid();
        this.product = new ResponseProductData(entity.getProduct());
        this.user = new ResponseUserData(entity.getUser());
        this.quantity = entity.getQuantity();
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public ResponseProductData getProduct() {
        return product;
    }

    public void setProduct(ResponseProductData product) {
        this.product = product;
    }

    public ResponseUserData getUser() {
        return user;
    }

    public void setUser(ResponseUserData user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
