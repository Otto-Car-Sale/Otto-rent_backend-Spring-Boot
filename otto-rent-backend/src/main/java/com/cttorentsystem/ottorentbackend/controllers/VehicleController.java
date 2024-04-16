package com.cttorentsystem.ottorentbackend.controllers;

import com.cttorentsystem.ottorentbackend.dtos.VehicleDto;
import com.cttorentsystem.ottorentbackend.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@AllArgsConstructor
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private VehicleService  vehicleService; //VehicleService

    @RequestMapping
   public ResponseEntity<VehicleDto> createVehicle(@RequestBody VehicleDto vehicleDto) {

        VehicleDto saveVehicle = vehicleService.createVehicle(vehicleDto);

        return new  ResponseEntity<> (saveVehicle, HttpStatus.CREATED);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable("vehicleId") Long vehicleId) {

        VehicleDto vehicleDto = vehicleService.getVehicleById(vehicleId);
        return ResponseEntity.ok(vehicleDto);

    }

    @GetMapping
    public ResponseEntity<List<VehicleDto>> getAllVehicles() {
        List<VehicleDto> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @PostMapping("/{vehicleId}")
    public ResponseEntity<VehicleDto> updateVehicle(@PathVariable("vehicleId") Long vehicleId , @RequestBody VehicleDto vehicleDto){
        VehicleDto updateVehicle  = vehicleService.updateVehicle(vehicleDto,vehicleId);
        return ResponseEntity.ok(updateVehicle);
    }



}
