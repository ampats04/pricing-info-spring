package com.accenture.pricinginfo.controller;

import com.accenture.pricinginfo.dto.PricingForProductResponse;
import com.accenture.pricinginfo.dto.PricingInitializationResponse;
import com.accenture.pricinginfo.dto.ValidatePricingRequest;
import com.accenture.pricinginfo.dto.ValidatePricingResponse;
import com.accenture.pricinginfo.service.PricingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ms-pricing-info")
public class PricingController {

    private final PricingService pricingService;

    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @PostMapping("/initializeDatabase")
    public PricingInitializationResponse initializePricing() {
        return this.pricingService.initializeDatabase();
    }

    @GetMapping("getPricing/{productCode}")
    public PricingForProductResponse getPricing(@PathVariable String productCode){ return pricingService.getPricing(productCode); }

    @PostMapping("/validatePricing")
    public ValidatePricingResponse validatePricing(@RequestBody ValidatePricingRequest request){ return pricingService.validatePricing(request);}
}
