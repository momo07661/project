package com.fsse2401.project.data.stripe.domainObject.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StripeRequestDto {
    private Long quantity;
    @JsonProperty("stripe_price_id")
    private String priceId;

    public StripeRequestDto(Long quantity, String priceId) {
        this.quantity = quantity;
        this.priceId = priceId;
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
