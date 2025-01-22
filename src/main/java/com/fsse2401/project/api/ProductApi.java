package com.fsse2401.project.api;

import com.fsse2401.project.config.EnvConfig;
import com.fsse2401.project.data.product.domainObject.response.ResponseProductData;
import com.fsse2401.project.data.product.dto.request.PutRequestProductDto;
import com.fsse2401.project.data.product.dto.response.PutResponseProductDto;
import com.fsse2401.project.data.product.dto.response.ResponseAllProductDto;
import com.fsse2401.project.data.product.dto.response.ResponsePidProductDto;
import com.fsse2401.project.service.ProductService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.fsse2401.project.config.EnvConfig.PRO_S3_BASE_URL;

@CrossOrigin({EnvConfig.DEV_BASE_URL, EnvConfig.PRO_BASE_URL, PRO_S3_BASE_URL, EnvConfig.DEV_BASE_URL2})
@RestController
public class ProductApi {
    @Autowired
    private final ProductService productService;

    public ProductApi(ProductService productService) {
        this.productService = productService;
    }

//    DEV: stripe product method
//    @PutMapping("/public/stripe")
//    public void updateStripeProduct() throws StripeException {
//        productService.updateProductEntityStripe();
//    }
//
//    @DeleteMapping("/public/stripe")
//    public void clearStripeProduct() throws StripeException {
//        productService.clearAllProductStripe();
//    }

    @GetMapping("/public/product")
    public List<ResponseAllProductDto> getAllProduct(){
        List<ResponseAllProductDto> productList = new ArrayList<>();
        for (ResponseProductData data: productService.getAllProducts()){
            productList.add(new ResponseAllProductDto(data));
        }
        return productList;
    }

    @GetMapping("/public/product/{id}")
    public ResponsePidProductDto getProductByPid(@PathVariable Integer id){
        return new ResponsePidProductDto(productService.getProductByPid(id));
    }

    @GetMapping("/public/product/name/{name}")
    public ResponsePidProductDto getProductContainName(@PathVariable String name){
        return new ResponsePidProductDto(productService.getProductByName(name));
    }

    @PutMapping("/public/product")
    public PutResponseProductDto createProduct(@RequestBody PutRequestProductDto requestProductDto){
        return new PutResponseProductDto(productService.createProductEntity(requestProductDto.getName(), requestProductDto.getImageUrl(), requestProductDto.getPrice(), requestProductDto.getStock(), requestProductDto.getDescription()));
    }
}
