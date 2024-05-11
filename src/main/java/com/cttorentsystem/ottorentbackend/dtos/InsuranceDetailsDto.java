package com.cttorentsystem.ottorentbackend.dtos;

import com.cttorentsystem.ottorentbackend.entity.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceDetailsDto {

    private Long insuranceDetailsId;
    private String coverageType;
    private String companyName;
    private String policyholderName;
    private String coveragePeriod;
    private Vehicle vehicleDetails;
    private String premiumAmount;
    private String policyExclusions;
    private LocalDate renewalDate;
    private String contactNumber;
    private String claimProcess;
}
