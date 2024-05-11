package com.cttorentsystem.ottorentbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "service_details")
public class ServiceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceDetailsId; // Unique identifier for the service details.
    private LocalDate serviceDate; // Date when the service was performed.
    private String serviceCenterName; // Name of the service center where the service was performed.
    private String serviceCenterLocation; // Location of the service center.
    private String travelDistance; // Distance traveled by the vehicle since the last service.
    private String serviceCost; // Cost of the service.
    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicleId")
    @JsonIgnore
    private Vehicle vehicleId; // Vehicle associated with these service details.
    @OneToMany(cascade = CascadeType.ALL)
    private List<ChangedPart> changedParts = new ArrayList<>(); // List of parts that were changed during the service.
    private String driverName; // Name of the driver who availed the service.
    private String driverPhone; // Phone number of the driver.
    private LocalDate nextServiceDate; // Date for the next scheduled service.
    private String nextServiceDistance; // Distance at which the next service is due.




}
