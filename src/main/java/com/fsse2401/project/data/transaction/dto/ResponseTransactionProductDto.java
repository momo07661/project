package com.fsse2401.project.data.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2401.project.data.product.domainObject.response.ResponseProductData;
import com.fsse2401.project.data.product.dto.response.ResponsePidProductDto;
import com.fsse2401.project.data.transaction.domainObject.ResponseTransactionProductData;


import java.math.BigDecimal;

public class ResponseTransactionProductDto {
    private Integer tpid;
    private Integer tid;
    private ResponsePidProductDto product;
    private Integer quantity;
    private BigDecimal subtotal;

    public ResponseTransactionProductDto(ResponseTransactionProductData data) {
        this.tpid = data.getTpid();
        this.tid = data.getTid();
        this.product = new ResponsePidProductDto(data.getProduct());
        this.quantity = data.getQuantity();
        this.subtotal = data.getSubtotal();
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

    public ResponsePidProductDto getProduct() {
        return product;
    }

    public void setProduct(ResponsePidProductDto product) {
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
