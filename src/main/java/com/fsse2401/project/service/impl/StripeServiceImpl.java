package com.fsse2401.project.service.impl;


import com.fsse2401.project.data.product.entity.ProductEntity;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.model.ProductCollection;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import com.stripe.param.ProductListParams;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StripeServiceImpl {
    public ProductEntity createProductWithStripeData(ProductEntity product) throws StripeException {
        Stripe.apiKey = "sk_test_51P7untCKEIE0doCRjd1VhCtVUgiQPWcWzdlQfJZa3NCHac1oes3tYe4QSd09WWeYtniGfc30ULEMRL54yyKJWxWF00wxI5uBUH";

        // Create or update product in Stripe
        ProductCreateParams productParams = ProductCreateParams.builder()
                .setName(product.getName())
                .setDescription(product.getDescription())
                .addImage(product.getImageUrl())
                .build();
        Product stripeProduct = Product.create(productParams);

        // Create price for the product in Stripe
        PriceCreateParams priceParams = PriceCreateParams.builder()
                .setCurrency("hkd")
                .setProduct(stripeProduct.getId())
                .setUnitAmount(product.getPrice().multiply(BigDecimal.valueOf(100)).longValue()) // Set your price amount here
                .build();
        Price price = Price.create(priceParams);

        // Update product entity with Stripe IDs
        product.setStripePriceId(price.getId());

        // Save product entity to MySQL database
        return product;
    }

    public void clearAllStripeProduct() throws StripeException{
        Stripe.apiKey = "sk_test_51P7untCKEIE0doCRjd1VhCtVUgiQPWcWzdlQfJZa3NCHac1oes3tYe4QSd09WWeYtniGfc30ULEMRL54yyKJWxWF00wxI5uBUH";
        ProductListParams params = ProductListParams.builder()
                .setLimit(100L) // Maximum number of products to retrieve (default is 10)
                .setActive(true)
                .build();
        ProductCollection products = Product.list(params);

        for(Product product: products.getData()){
                System.out.println(product.getName());
            product.delete();
            System.out.println("Product deleted:".concat(product.getId()));
        }
    }
}
