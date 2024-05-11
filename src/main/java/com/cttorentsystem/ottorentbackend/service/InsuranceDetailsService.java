package com.cttorentsystem.ottorentbackend.service;

import com.cttorentsystem.ottorentbackend.dtos.InsuranceDetailsDto;

public interface InsuranceDetailsService {

    InsuranceDetailsDto addInsuranceDetails(InsuranceDetailsDto insuranceDetailsDto);

    void triggerInsuranceExpiryDateDate();
}
