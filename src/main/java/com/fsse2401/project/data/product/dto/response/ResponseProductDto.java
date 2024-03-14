package com.fsse2401.project.data.product.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;


public class ResponseProductDto {

    private Integer pid;

    private String name;

    @JsonProperty("image_url")
    private String imageUrl;
    private BigDecimal price;

    @JsonProperty("has_stock")
    private Boolean isStock;

    public ResponseProductDto() {
        this.pid = pid;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.isStock = isStock;
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

    public Boolean getStock() {
        return isStock;
    }

    public void setStock(Boolean stock) {
        isStock = stock;
    }
}
