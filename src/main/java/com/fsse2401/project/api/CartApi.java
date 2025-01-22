package com.fsse2401.project.api;

import com.fsse2401.project.config.EnvConfig;
import com.fsse2401.project.data.cart.domainObject.response.ResponseCartData;
import com.fsse2401.project.data.cart.dto.response.*;
import com.fsse2401.project.data.user.domainObject.FireBaseUserData;
import com.fsse2401.project.data.user.domainObject.ResponseUserData;
import com.fsse2401.project.exception.exceptionList.MissingRequiredDataException;
import com.fsse2401.project.service.CartItemService;
import com.fsse2401.project.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.fsse2401.project.config.EnvConfig.PRO_S3_BASE_URL;

@RestController
@CrossOrigin({EnvConfig.DEV_BASE_URL, EnvConfig.PRO_BASE_URL, PRO_S3_BASE_URL})
@RequestMapping("/cart")
public class CartApi {
    @Autowired
    private final CartItemService cartItemService;

    public CartApi(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping("/{pid}")
    public Integer getCartQuantityByPid(JwtAuthenticationToken jwtToken, @PathVariable Integer pid){
        if (cartItemService.getCartItemByPid(JwtUtil.getFirebaseUserData(jwtToken), pid) == null){
            GetQuantityResponseCartDto temDto = new GetQuantityResponseCartDto(0);
        }
        GetQuantityResponseCartDto temDto = new GetQuantityResponseCartDto(cartItemService.getCartItemByPid(JwtUtil.getFirebaseUserData(jwtToken), pid));
        return 0;
    }

    @PutMapping("/{pid}/{quantity}")
    public PutResponseCartDto putCartItem(JwtAuthenticationToken jwtToken, @PathVariable Integer pid, @PathVariable Integer quantity){
        return new PutResponseCartDto(cartItemService.putCartItem(JwtUtil.getFirebaseUserData(jwtToken), pid, quantity));
    }

    @GetMapping
    public List<GetResponseCartDto> getUserCartItem(JwtAuthenticationToken jwtToken){
        List<GetResponseCartDto> responseCartDtoList = new ArrayList<>();
        for (ResponseCartData data: cartItemService.getCartItem(JwtUtil.getFirebaseUserData(jwtToken))){
            responseCartDtoList.add(new GetResponseCartDto(data));
        }

        return responseCartDtoList;
    }

    @PatchMapping("/{pid}/{quantity}")
    public PatchResponseCartDto updateCartQuantity(JwtAuthenticationToken jwtToken, @PathVariable Integer pid, @PathVariable Integer quantity){
        return new PatchResponseCartDto(cartItemService.updateCartQuantity(JwtUtil.getFirebaseUserData(jwtToken), pid, quantity));
    }

    @DeleteMapping("/{pid}")
    public DeleteResponseCartDto deleteCartItem(JwtAuthenticationToken jwtToken, @PathVariable Integer pid){
        return new DeleteResponseCartDto(cartItemService.deleteCartItem(JwtUtil.getFirebaseUserData(jwtToken), pid));
    }
}
