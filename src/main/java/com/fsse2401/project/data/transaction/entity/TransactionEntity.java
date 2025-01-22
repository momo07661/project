package com.fsse2401.project.data.transaction.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2401.project.data.user.entity.UserEntity;
import com.fsse2401.project.util.TransactionState;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;
    @JoinColumn(name = "buyer_uid", nullable = false)
    @ManyToOne
    private UserEntity user;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionState status;
    @Column(nullable = false)
    private BigDecimal total = new BigDecimal(0);

    @OneToMany(mappedBy = "transaction")
    private List<TransactionProductEntity> items = new ArrayList<>();

    @Column(nullable = true)
    @JsonProperty("stripe_session_id")
    private String stripeSessionId;

    public TransactionEntity() {
    }

    public TransactionEntity(UserEntity user) {
        this.user = user;
        this.dateTime = LocalDateTime.now();
        this.status = TransactionState.PREPARE;
    }

    public String getStripeSessionId() {
        return stripeSessionId;
    }

    public void setStripeSessionId(String stripeSessionId) {
        this.stripeSessionId = stripeSessionId;
    }

    public void setSumPrice(List<TransactionProductEntity> itemList){
        for (TransactionProductEntity entity: itemList){
            total = total.add(entity.getPrice().multiply(new BigDecimal(entity.getQuantity())));
        }
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public TransactionState getStatus() {
        return status;
    }

    public void setStatus(TransactionState status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TransactionProductEntity> getItems() {
        return items;
    }

    public void setItems(List<TransactionProductEntity> items) {
        this.items = items;
        setSumPrice(items);
    }
}
