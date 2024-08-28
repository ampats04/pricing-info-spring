package com.accenture.pricinginfo.dto;

import com.accenture.pricinginfo.model.entity.Pricing;

import java.util.List;

public class PricingInitializationResponse {

    private List<Pricing> list;

    public PricingInitializationResponse(List<Pricing> list) {
        this.list = list;
    }

    public List<Pricing> getList() {
        return list;
    }

    public void setList(List<Pricing> list) {
        this.list = list;
    }
}
