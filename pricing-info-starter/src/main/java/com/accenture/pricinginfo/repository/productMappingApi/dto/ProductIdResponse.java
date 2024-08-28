package com.accenture.pricinginfo.repository.productMappingApi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductIdResponse {
    private String productId;

    public ProductIdResponse() {
    }

    public ProductIdResponse(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
