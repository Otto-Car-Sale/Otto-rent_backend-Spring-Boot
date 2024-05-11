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
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "licence")
public class Licence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long licenceId;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicleId")
    @JsonIgnore
    private Vehicle vehicleId;
    private String licenseNumber;
    private LocalDate expiryDate;
    private LocalDate  issueDate;
    private String issueProvince;

}
