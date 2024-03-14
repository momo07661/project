package com.fsse2401.project.data.product.domainObject.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ResponseProductData {
    private Integer pid;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private Boolean isStock;

    public ResponseProductData(Integer pid, String name, String imageUrl, BigDecimal price, Boolean isStock) {
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
