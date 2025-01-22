package com.fsse2401.project.data.transaction.domainObject;

import com.fsse2401.project.data.product.domainObject.response.ResponseProductData;
import com.fsse2401.project.data.product.entity.ProductEntity;
import com.fsse2401.project.data.transaction.entity.TransactionProductEntity;

import java.math.BigDecimal;

public class ResponseTransactionProductData {
    private Integer tpid;
    private Integer tid;
    private ResponseProductData product;
    private Integer quantity;
    private BigDecimal subtotal;

    public ResponseTransactionProductData(TransactionProductEntity entity) {
        this.tpid = entity.getTpid();
        this.tid = entity.getTransaction().getTid();
        this.product = new ResponseProductData(new ProductEntity(entity.getPid(), entity.getName(), entity.getImageUrl(), entity.getDescription(), entity.getPrice(), entity.getStock()));
        this.quantity = entity.getQuantity();
        this.subtotal = entity.getPrice().multiply(new BigDecimal(entity.getQuantity()));
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public ResponseProductData getProduct() {
        return product;
    }

    public void setProduct(ResponseProductData product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
