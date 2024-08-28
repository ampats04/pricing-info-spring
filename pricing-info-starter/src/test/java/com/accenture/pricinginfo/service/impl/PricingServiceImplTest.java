package com.accenture.pricinginfo.service.impl;

import com.accenture.pricinginfo.dto.PricingForProductResponse;
import com.accenture.pricinginfo.dto.ValidatePricingRequest;
import com.accenture.pricinginfo.dto.ValidatePricingResponse;
import com.accenture.pricinginfo.exception.BadRequestException;
import com.accenture.pricinginfo.repository.PricingRepository;
import com.accenture.pricinginfo.repository.productMappingApi.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PricingServiceImplTest {

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public PricingRepository pricingRepository;

    @Test
    void testGetPricing_ValidProductCodeAndValidProductId(){

        // Arrange
        String productCode = "123456";
        PricingServiceImpl pricingService = new PricingServiceImpl(pricingRepository, productRepository);

        PricingForProductResponse expectedResponse = new PricingForProductResponse(
                new BigDecimal("0.20"),
                new BigDecimal("1000.00"),
                new BigDecimal("100000.00"),
                "1_YEAR"
        );


        // Act
        PricingForProductResponse response = pricingService.getPricing(productCode);


        // Assert
        assertEquals(expectedResponse.getInterestRate(), response.getInterestRate());
        assertEquals(expectedResponse.getMinDepositAmount(), response.getMinDepositAmount());
        assertEquals(expectedResponse.getMaxDepositAmount(), response.getMaxDepositAmount());
        assertEquals(expectedResponse.getMinAllowedTerm(), response.getMinAllowedTerm());
    }


    @Test
    void testGetPricing_ValidProductCodeButInvalidProductId() {
        // Arrange
        String productCode = "123451";
        PricingServiceImpl pricingService = new PricingServiceImpl(pricingRepository, productRepository);


        // Act & Assert
        assertThrows(BadRequestException.class, () -> pricingService.getPricing(productCode));
    }

    @Test
    void testGetPricing_InvalidProductCode(){
        // Arrange
        String productCode = "12345";
        PricingServiceImpl pricingService = new PricingServiceImpl(pricingRepository, productRepository);
        BadRequestException ExpectedException = new BadRequestException(400, String.format("Product code %s not found", productCode), Collections.emptyMap());

        //Act and Assert
        assertThrows(ExpectedException.getClass(), () -> pricingService.getPricing(productCode));
    }

    @Test
    void testValidatePricing_CorrectDetails(){
        // Arrange
        PricingServiceImpl pricingService = new PricingServiceImpl(pricingRepository, productRepository);

        ValidatePricingRequest TestPricing = new ValidatePricingRequest(
                "1234567",
                new BigDecimal("0.20"),
                "1_YEAR"
        );

        ValidatePricingResponse expectedResponse = new ValidatePricingResponse(
                "",
                true
        );

        // Act
        ValidatePricingResponse response = pricingService.validatePricing(TestPricing);

        //Assert
        assertEquals(expectedResponse.getNotValidReason(), response.getNotValidReason());
        assertEquals(expectedResponse.getIsValid(), response.getIsValid());
    }


    @Test
    void testValidatePricing_InvalidProductId(){
        //Arrange
        PricingServiceImpl pricingService = new PricingServiceImpl(pricingRepository, productRepository);

        ValidatePricingRequest TestPricing = new ValidatePricingRequest(
                "123456",
                new BigDecimal("0.20"),
                "1_YEAR"
        );

        //Act & Assert
        assertThrows(BadRequestException.class, () -> pricingService.validatePricing(TestPricing));



    }

    @Test
    void testValidatePricing_ValidProductIdButInvalidInterestRate(){
        //Arrange
        PricingServiceImpl pricingService = new PricingServiceImpl(pricingRepository, productRepository);

        ValidatePricingRequest TestPricing = new ValidatePricingRequest(
                "1234567",
                new BigDecimal("0.30"),
                "1_YEAR"
        );

        ValidatePricingResponse expectedResponse = new ValidatePricingResponse(
                "The interest rate is incorrect.",
                false
        );

        //Act
        ValidatePricingResponse response = pricingService.validatePricing(TestPricing);

        //Assert
        assertEquals(expectedResponse.getNotValidReason(), response.getNotValidReason());
        assertEquals(expectedResponse.getIsValid(), response.getIsValid());
    }

    @Test
    void testValidatePricing_ValidProductIdButInvalidTerm(){
        //Arrange
        PricingServiceImpl pricingService = new PricingServiceImpl(pricingRepository, productRepository);

        ValidatePricingRequest TestPricing = new ValidatePricingRequest(
                "1234567",
                new BigDecimal("0.20"),
                "1_MONTH"
        );

        ValidatePricingResponse expectedResponse = new ValidatePricingResponse(
                "The term is too short.",
                false
        );

        //Act
        ValidatePricingResponse response = pricingService.validatePricing(TestPricing);

        //Assert
        assertEquals(expectedResponse.getNotValidReason(), response.getNotValidReason());
        assertEquals(expectedResponse.getIsValid(), response.getIsValid());
    }

}
