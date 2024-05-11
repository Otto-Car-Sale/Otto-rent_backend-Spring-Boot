package com.cttorentsystem.ottorentbackend.service;

import com.cttorentsystem.ottorentbackend.dtos.VehicleDto;
import com.cttorentsystem.ottorentbackend.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    VehicleDto createVehicle(VehicleDto vehicleDto);

    VehicleDto getVehicleById(Long vehicleId);

    List<VehicleDto> getAllVehicles();

    VehicleDto updateVehicle(VehicleDto vehicleDto, Long vehicleId);

    VehicleDto deleteVehicle(Long vehicleId);



    List<VehicleDto> suggestVehicle(Vehicle.VehicleType vehicleType, Vehicle.FuelType fuelType, int seatingCapacity);



}
