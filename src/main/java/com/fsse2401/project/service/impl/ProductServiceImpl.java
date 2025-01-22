package com.fsse2401.project.service.impl;

import com.fsse2401.project.data.product.domainObject.response.ResponseProductData;
import com.fsse2401.project.data.product.entity.ProductEntity;
import com.fsse2401.project.exception.exceptionList.DataNotFoundException;
import com.fsse2401.project.exception.exceptionList.MissingRequiredDataException;
import com.fsse2401.project.exception.exceptionList.StockQuantityNotEnoughException;
import com.fsse2401.project.repository.ProductRepository;
import com.fsse2401.project.service.ProductService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final StripeServiceImpl stripeService;

    public ProductServiceImpl(ProductRepository productRepository, StripeServiceImpl stripeService) {
        this.productRepository = productRepository;
        this.stripeService = stripeService;
    }

    @Override
    public List<ResponseProductData> getAllProducts(){
        List<ResponseProductData> productList = new ArrayList<>();
        for (ProductEntity entity: productRepository.getAll()){
            productList.add(new ResponseProductData(entity));
        }
        if (productList.isEmpty()){
            throw new DataNotFoundException();
        }
        return productList;
    }
    @Override
    public ResponseProductData getProductByPid(Integer pid){
        if (pid == null){
            throw new MissingRequiredDataException();
        }
        return new ResponseProductData(getProductEntityByPid(pid));
    }

    @Override
    public ProductEntity getProductEntityByPid(Integer pid){
        return productRepository.getByPid(pid).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public boolean isValidQuantity(ProductEntity entity, Integer quantity){
        if (entity == null || quantity == null){
            throw new MissingRequiredDataException();
        }

        if (quantity <= 0){
            throw new MissingRequiredDataException();
        }

        if (entity.getStock() < quantity){
            throw new StockQuantityNotEnoughException(entity.getStock(), quantity);
        }

        return true;
    }
    @Override
    public void updateProductEntity(ProductEntity entity, Integer quantity){
        if (isValidQuantity(entity, quantity)){
            entity.setStock(entity.getStock() - quantity);
            productRepository.save(entity);
        }
    }



    @Override
    public ResponseProductData getProductByName(String name){
        return new ResponseProductData(productRepository.getAllByName(name).orElseThrow(DataNotFoundException::new));
    }

    @Override
    public ResponseProductData createProductEntity(String name, String image_url, BigDecimal price, Integer stock, String description){
        ProductEntity productEntity = productRepository.save( new ProductEntity(name, image_url, description, price, stock));
        if (productEntity.getPid() == null){
            System.out.println("save product error");
            throw new DataNotFoundException();
        }
        return new ResponseProductData(productEntity);
    }

    @Override
    public void updateProductEntityStripe() throws StripeException {
        for (ProductEntity product: productRepository.getAll()){
            productRepository.save(stripeService.createProductWithStripeData(product));
        }
    }

    @Override
    public void clearAllProductStripe() throws StripeException{
        stripeService.clearAllStripeProduct();
    }
}
