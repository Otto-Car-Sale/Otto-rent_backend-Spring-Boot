package com.cttorentsystem.ottorentbackend.dtos;

import com.cttorentsystem.ottorentbackend.entity.Vehicle;
import jakarta.persistence.Entity;
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
public class LicenceDto {

    private Long licenceId;
    private Vehicle vehicleId;
    private String licenseNumber;
    private LocalDate expiryDate;
    private LocalDate  issueDate;
    private String issueProvince;

}
