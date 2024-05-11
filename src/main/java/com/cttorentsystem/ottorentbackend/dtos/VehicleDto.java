package com.cttorentsystem.ottorentbackend.dtos;

import com.cttorentsystem.ottorentbackend.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {


    private Long vehicleId;
    private String registrationNo;
    private String vehiclePrice;
    private String chassisNumber;
    private String engineNo;
    private String vehicleState;
    private String companyName;
    private int numberOfDoors;
    private String color;
    private int seatingCapacity;
    private String condition;
    private Dimensions dimensions;
    private int cylinderCapacity;
    private Vehicle.FuelType fuelType;
    private String manufacturedCountry;
    private boolean assembled;
    private Vehicle.VehicleType vehicleType;
    private String brand;
    private String style;
    private String model;
    private int manufacturedYear;
    private List<String> albumUrls;
    private List<Licence> licenses = new ArrayList<>();
    private List<ServiceDetails> serviceDetails = new ArrayList<>();
    private List<InsuranceDetails> insuranceDetails = new ArrayList<>();

    // Embedded class for Dimensions
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Dimensions {
        private double length;
        private double height;
        private double width;
    }

    // Enum for FuelType
    public enum FuelType {
        Petrol, Diesel, Electric, Hybrid, CNG
    }

    // Enum for VehicleType
    public enum VehicleType {
        car, bike, van, truck, cab
    }
}