package com.cttorentsystem.ottorentbackend.controllers;

import com.cttorentsystem.ottorentbackend.dtos.SuggestVehicleRequest;
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
@CrossOrigin(origins = "http://localhost:3000/")
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


    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<String> deleteVehicle(@PathVariable("vehicleId") Long vehicleId){
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }


    @GetMapping("/suggestVehicle")
    public ResponseEntity<?> suggestVehicle(@RequestBody SuggestVehicleRequest suggestVehicleRequest){

    try {
          List<VehicleDto> vehicles = vehicleService.suggestVehicle(
                  suggestVehicleRequest.getVehicleType(),
                  suggestVehicleRequest.getFuelType(),
                  suggestVehicleRequest.getSeatingCapacity()
          );

        return ResponseEntity.ok(vehicles);

    } catch (Exception e) {
          Logger.getLogger(VehicleController.class.getName()).warning(e.getMessage());
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurred: " + e.getMessage());
    }

    }



}
