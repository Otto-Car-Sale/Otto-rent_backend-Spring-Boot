package com.cttorentsystem.ottorentbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "insurance_details")
public class InsuranceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long insuranceDetailsId; // Unique identifier for the insurance details.
    private String coverageType; // Type of coverage provided by the insurance.
    private String companyName; // Name of the insurance company.
    private String policyholderName; // Name of the policyholder.
    private String coveragePeriod; // Duration for which the insurance coverage is valid.
    @ManyToOne
    @JoinColumn(name = "vehicle", referencedColumnName = "vehicleId")
    @JsonIgnore
    private Vehicle vehicleDetails; // The vehicle associated with these insurance details.
    private String premiumAmount; // Cost of the insurance premium.
    private String policyExclusions; // Details of what is not covered by the insurance policy.
    private LocalDate renewalDate; // Date by which the insurance policy needs to be renewed.
    private String contactNumber; // Contact number for inquiries or claims.
    private String claimProcess; // Information on how to file a claim.

}
