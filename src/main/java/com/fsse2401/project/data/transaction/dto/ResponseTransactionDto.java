package com.fsse2401.project.data.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2401.project.data.transaction.domainObject.ResponseTransactionData;
import com.fsse2401.project.data.transaction.domainObject.ResponseTransactionProductData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ResponseTransactionDto {
    private Integer tid;
    @JsonProperty("buyer_id")
    private Integer buyerId;
    private LocalDateTime dateTime;

    private String status;
    private BigDecimal total;
    private List<ResponseTransactionProductDto> items = new ArrayList<>();

    @JsonProperty("stripe_session_id")
    private String stripeSessionId;

    public ResponseTransactionDto(ResponseTransactionData data) {
        this.tid = data.getTid();
        this.buyerId = data.getBuyerId();
        this.dateTime = data.getDateTime();
        this.status = data.getStatus();
        this.total = data.getTotal();
        this.items = toDtoList(data);
        this.stripeSessionId = data.getStripeSessionId();
    }

    public List<ResponseTransactionProductDto> toDtoList(ResponseTransactionData data){
        List<ResponseTransactionProductDto> itemDtoList = new ArrayList<>();
        for (ResponseTransactionProductData temData: data.getItems()){
            itemDtoList.add(new ResponseTransactionProductDto(temData));
        }
        return  itemDtoList;
    }

    public Integer getTid() {
        return tid;
    }

    public String getStripeSessionId() {
        return stripeSessionId;
    }

    public void setStripeSessionId(String stripeSessionId) {
        this.stripeSessionId = stripeSessionId;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<ResponseTransactionProductDto> getItems() {
        return items;
    }

    public void setItems(List<ResponseTransactionProductDto> items) {
        this.items = items;
    }
}
