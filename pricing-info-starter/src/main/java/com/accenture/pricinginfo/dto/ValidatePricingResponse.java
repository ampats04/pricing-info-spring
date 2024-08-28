package com.accenture.pricinginfo.dto;

public class ValidatePricingResponse {

    private boolean isValid;
    private String notValidReason;

    public ValidatePricingResponse(String notValidReason, boolean isValid) {
        this.isValid = isValid;
        this.notValidReason = notValidReason;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getNotValidReason() {
        return notValidReason;
    }

    public void setNotValidReason(String notValidReason) {
        this.notValidReason = notValidReason;
    }
}
