package com.fsse2401.project.service;

import com.fsse2401.project.data.product.domainObject.response.ResponseProductData;
import com.fsse2401.project.data.product.entity.ProductEntity;
import com.stripe.exception.StripeException;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ResponseProductData> getAllProducts();

    ResponseProductData getProductByPid(Integer id);

    ProductEntity getProductEntityByPid(Integer pid);

    boolean isValidQuantity(ProductEntity entity, Integer quantity);

    void updateProductEntity(ProductEntity entity, Integer quantity);

    void updateProductEntityStripe() throws StripeException;

    ResponseProductData getProductByName(String name);

    ResponseProductData createProductEntity(String name, String image_url, BigDecimal price, Integer stock, String description);

    void clearAllProductStripe() throws StripeException;
}
