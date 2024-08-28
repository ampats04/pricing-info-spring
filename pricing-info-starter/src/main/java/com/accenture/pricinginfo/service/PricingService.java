package com.accenture.pricinginfo.service;

import com.accenture.pricinginfo.dto.PricingForProductResponse;
import com.accenture.pricinginfo.dto.PricingInitializationResponse;
import com.accenture.pricinginfo.dto.ValidatePricingRequest;
import com.accenture.pricinginfo.dto.ValidatePricingResponse;

public interface PricingService {

    PricingInitializationResponse initializeDatabase();
    PricingForProductResponse getPricing(String productCode);
    ValidatePricingResponse validatePricing(ValidatePricingRequest request);
}
