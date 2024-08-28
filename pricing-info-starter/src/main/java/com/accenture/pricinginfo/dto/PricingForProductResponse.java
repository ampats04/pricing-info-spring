package com.accenture.pricinginfo.dto;

import java.math.BigDecimal;

public class PricingForProductResponse {

    private BigDecimal interestRate;
    private BigDecimal minDepositAmount;

    public PricingForProductResponse(BigDecimal interestRate, BigDecimal minDepositAmount, BigDecimal maxDepositAmount, String minAllowedTerm) {
        this.interestRate = interestRate;
        this.minDepositAmount = minDepositAmount;
        this.maxDepositAmount = maxDepositAmount;
        this.minAllowedTerm = minAllowedTerm;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getMinDepositAmount() {
        return minDepositAmount;
    }

    public void setMinDepositAmount(BigDecimal minDepositAmount) {
        this.minDepositAmount = minDepositAmount;
    }

    public BigDecimal getMaxDepositAmount() {
        return maxDepositAmount;
    }

    public void setMaxDepositAmount(BigDecimal maxDepositAmount) {
        this.maxDepositAmount = maxDepositAmount;
    }

    public String getMinAllowedTerm() {
        return minAllowedTerm;
    }

    public void setMinAllowedTerm(String minAllowedTerm) {
        this.minAllowedTerm = minAllowedTerm;
    }

    private BigDecimal maxDepositAmount;
    private String minAllowedTerm;
}
