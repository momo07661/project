package com.fsse2401.project.data.cart.dto.response;

import com.fsse2401.project.data.cart.domainObject.response.ResponseCartData;

public class PutResponseCartDto {
    private String result;

    public PutResponseCartDto(ResponseCartData data) {
        if (data!=null){
            result = "SUCCESS";
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
