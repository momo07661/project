package com.fsse2401.project.data.transaction.domainObject;
import com.fsse2401.project.data.transaction.entity.TransactionEntity;
import com.fsse2401.project.data.transaction.entity.TransactionProductEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ResponseTransactionData {
    private Integer tid;
    private Integer buyerId;
    private LocalDateTime dateTime;
    private String status;
    private BigDecimal total;
    private List<ResponseTransactionProductData> items = new ArrayList<>();
    private String stripeSessionId;

    public ResponseTransactionData(TransactionEntity entity) {
        this.tid = entity.getTid();
        this.buyerId = entity.getUser().getUid();
        this.dateTime = entity.getDateTime();
        this.status = entity.getStatus().name();
        this.total = entity.getTotal();
        this.items = setItems(entity);
        this.stripeSessionId = entity.getStripeSessionId();
    }

    private List<ResponseTransactionProductData> setItems(TransactionEntity entity){
        List<ResponseTransactionProductData> dataList = new ArrayList<>();
        for (TransactionProductEntity temEntity: entity.getItems()){
            dataList.add(new ResponseTransactionProductData(temEntity));
        }
        return dataList;
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

    public List<ResponseTransactionProductData> getItems() {
        return items;
    }

    public void setItems(List<ResponseTransactionProductData> items) {
        this.items = items;
    }
}
