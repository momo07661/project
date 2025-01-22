package com.fsse2401.project.data.product.dto.response;

import com.fsse2401.project.data.product.domainObject.response.ResponseProductData;

public class PutResponseProductDto {
    private boolean status;

    public PutResponseProductDto(ResponseProductData data) {
        status = data != null;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
