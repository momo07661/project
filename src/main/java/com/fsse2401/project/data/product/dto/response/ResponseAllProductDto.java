package com.fsse2401.project.data.product.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2401.project.data.product.domainObject.response.ResponseProductData;

import java.math.BigDecimal;


public class ResponseAllProductDto {

    private Integer pid;

    private String name;

    @JsonProperty("image_url")
    private String imageUrl;
    private BigDecimal price;

    @JsonProperty("has_stock")
    private Boolean hasStock;

    public ResponseAllProductDto(ResponseProductData data) {
        this.pid = data.getPid();
        this.name = data.getName();
        this.imageUrl = data.getImageUrl();
        this.price = data.getPrice();
        this.hasStock = data.getStock() > 0;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getHasStock() {
        return hasStock;
    }

    public void setHasStock(Boolean hasStock) {
        this.hasStock = hasStock;
    }
}
