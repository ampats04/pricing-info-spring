package com.accenture.pricinginfo.service.impl;

import com.accenture.pricinginfo.dto.PricingForProductResponse;
import com.accenture.pricinginfo.dto.PricingInitializationResponse;
import com.accenture.pricinginfo.dto.ValidatePricingRequest;
import com.accenture.pricinginfo.dto.ValidatePricingResponse;
import com.accenture.pricinginfo.exception.BadRequestException;
import com.accenture.pricinginfo.model.entity.Pricing;
import com.accenture.pricinginfo.repository.productMappingApi.ProductRepository;
import com.accenture.pricinginfo.repository.PricingRepository;
import com.accenture.pricinginfo.repository.productMappingApi.dto.ProductIdResponse;
import com.accenture.pricinginfo.service.PricingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PricingServiceImpl implements PricingService {

    private final PricingRepository pricingRepository;

    private final ProductRepository productRepository;

    private String message;

    public PricingServiceImpl(
            PricingRepository pricingRepository,
            ProductRepository productRepository
    ) {
        this.pricingRepository = pricingRepository;
        this.productRepository = productRepository;
    }

    @Override
    public PricingInitializationResponse initializeDatabase() {
        Pricing pricing1 = buildPricing("1234567", 1, "YEAR");
        Pricing pricing2 = buildPricing("12345678", 1, "YEAR");
        Pricing pricing3 = buildPricing("123457", 1, "YEAR");
        Pricing pricing4 = buildPricing("1234573", 6, "MONTHS");
        Pricing pricing5 = buildPricing("1234572", 9, "MONTHS");
        Pricing pricing6 = buildPricing("12345671", 2, "YEARS");

        List<Pricing> pricings = pricingRepository.saveAll(
                List.of(
                        pricing1,
                        pricing2,
                        pricing3,
                        pricing4,
                        pricing5,
                        pricing6
                )
        );

        return new PricingInitializationResponse(
                pricings
        );
    }
//code

    public String getProductId(String productCode){

        ProductIdResponse response = productRepository.getProductId(productCode);

        return response != null ? response.getProductId() : null;
    }

    public boolean areInterestRatesEqual(Pricing pricing, ValidatePricingRequest request){

        if (pricing == null || request == null) {
            throw new IllegalArgumentException("Pricing or request cannot be null");
        }

        return pricing.getInterestRate()
                .compareTo(request.getInterestRate()) == 0;
    }

    public boolean isValidTerm(Pricing pricing, ValidatePricingRequest request){

       String term = request.getTerm();
       String[] arr = term.split("_");

       int term_number = Integer.parseInt(arr[0]);
       String term_date = arr[1];


    return pricing.getMinAllowedTerm() == term_number
            && pricing.getMinAllowedTermType()
            .equalsIgnoreCase(term_date);

    }

    @Override
    public PricingForProductResponse getPricing(String productCode) {

        Pricing pricing = pricingRepository.findById(this.getProductId(productCode))
                .orElse(null);

        if(pricing == null){
            message = "Pricing for product code " + productCode + " not found.";
            throw new BadRequestException(
                    404,
                    message,
                    Map.of()
            );
        }

        return new PricingForProductResponse(
                pricing.getInterestRate(),
                pricing.getMinDepositAmount(),
                pricing.getMaxDepositAmount(),
                pricing.getMinAllowedTerm() + "_" + pricing.getMinAllowedTermType()
        );
    }

    @Override
    public ValidatePricingResponse validatePricing(ValidatePricingRequest request) {

        String productId  = request.getProductId();
        Pricing pricing = pricingRepository.findById(productId)
                .orElse(null);

        if(pricing == null){
            throw new BadRequestException(
                    404,
                    "Product id " + productId + " not found.",
                    Map.of()
            );
        }else{
            if(!isValidTerm(pricing, request)){
                return new ValidatePricingResponse("The term is too short.",
                        false);

            } else if(!areInterestRatesEqual(pricing, request)){
                return new ValidatePricingResponse("The interest rate is incorrect.",
                        false);
            }

        }
        return new ValidatePricingResponse("",true);
    }

    //private code
    private Pricing buildPricing(
            String productId,
            int minAllowedTerm,
            String minAllowedTermType
    ) {
        return Pricing
                .builder(productId, minAllowedTerm, minAllowedTermType)
                .build();
    }
}
