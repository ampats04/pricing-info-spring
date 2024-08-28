package com.accenture.pricinginfo.dto;

import java.math.BigDecimal;

public class ValidatePricingRequest {

    private String productId;
    private BigDecimal interestRate;

    public ValidatePricingRequest(String productId, BigDecimal interestRate, String term) {
        this.productId = productId;
        this.interestRate = interestRate;
        this.term = term;
    }

    private String term;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
