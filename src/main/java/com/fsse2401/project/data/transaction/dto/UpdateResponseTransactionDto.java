package com.fsse2401.project.data.transaction.dto;

import com.fsse2401.project.data.transaction.domainObject.ResponseTransactionData;

public class UpdateResponseTransactionDto {
    private String result = "Fail";

    public UpdateResponseTransactionDto(ResponseTransactionData data) {
        if (data.getTotal().doubleValue() > 0){
            this.result = "SUCCESS";
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
