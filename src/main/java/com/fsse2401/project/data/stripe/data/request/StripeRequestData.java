package com.fsse2401.project.data.stripe.data.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2401.project.data.cart.domainObject.response.ResponseCartData;
import com.fsse2401.project.data.stripe.domainObject.request.StripeRequestDto;

public class StripeRequestData {
    private Long quantity;
    private String priceId;

    public StripeRequestData(StripeRequestDto dto) {
        this.quantity = dto.getQuantity();
        this.priceId = dto.getPriceId();
    }

    public StripeRequestData(ResponseCartData data) {
        this.quantity = (long) data.getQuantity();
        this.priceId = data.getProduct().getStripePriceId();
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }
}
