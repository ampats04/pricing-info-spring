package com.accenture.pricinginfo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "pricing")
public class Pricing {
    @Id
    @Column(columnDefinition = "VARCHAR(10)")
    private String productId;

    @Column(columnDefinition = "DECIMAL(10,2)", nullable = false)
    private BigDecimal minDepositAmount;

    @Column(columnDefinition = "DECIMAL(10,2)", nullable = false)
    private BigDecimal maxDepositAmount;

    @Column(nullable = false)
    private LocalDate effectiveDate;

    @Column(nullable = false)
    private int minAllowedTerm;

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private String minAllowedTermType;

    @Column(nullable = false)
    private BigDecimal interestRate;

    public Pricing() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public int getMinAllowedTerm() {
        return minAllowedTerm;
    }

    public void setMinAllowedTerm(int minAllowedTerm) {
        this.minAllowedTerm = minAllowedTerm;
    }

    public String getMinAllowedTermType() {
        return minAllowedTermType;
    }

    public void setMinAllowedTermType(String minAllowedTermType) {
        this.minAllowedTermType = minAllowedTermType;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public static class Builder {
        private final String productId;

        private final int minAllowedTerm;

        private final String minAllowedTermType;

        private BigDecimal minDepositAmount = new BigDecimal("1000.00");

        private BigDecimal maxDepositAmount = new BigDecimal("100000.00");

        private BigDecimal interestRate = new BigDecimal("0.2");

        private LocalDate effectiveDate =
                LocalDate.parse(
                        "2023-12-11",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")
                );

        public Builder(
                String productId,
                int minAllowedTerm,
                String minAllowedTermType
        ) {
            this.productId = productId;
            this.minAllowedTerm = minAllowedTerm;
            this.minAllowedTermType = minAllowedTermType;
        }

        public Builder minDepositAmount(BigDecimal val) {
            minDepositAmount = val;
            return this;
        }

        public Builder maxDepositAmount(BigDecimal val) {
            maxDepositAmount = val;
            return this;
        }

        public Builder interestRate(BigDecimal val) {
            interestRate = val;
            return this;
        }

        public Builder effectiveDate(LocalDate val) {
            effectiveDate = val;
            return this;
        }

        public Pricing build() {
            return new Pricing(this);
        }
    }

    public static Builder builder(
            String productId,
            int minAllowedTerm,
            String minAllowedTermType
    ) {
        return new Builder(productId, minAllowedTerm, minAllowedTermType);
    }

    private Pricing(Builder builder) {
        productId = builder.productId;
        minDepositAmount = builder.minDepositAmount;
        maxDepositAmount = builder.maxDepositAmount;
        effectiveDate = builder.effectiveDate;
        minAllowedTerm = builder.minAllowedTerm;
        minAllowedTermType = builder.minAllowedTermType;
        interestRate = builder.interestRate;
    }
}
