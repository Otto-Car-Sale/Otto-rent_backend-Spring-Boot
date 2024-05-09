package com.cttorentsystem.ottorentbackend.service.impl;

import com.cttorentsystem.ottorentbackend.controllers.EmailController;
import com.cttorentsystem.ottorentbackend.dtos.VehicleDto;
import com.cttorentsystem.ottorentbackend.entity.ServiceDetails;
import com.cttorentsystem.ottorentbackend.entity.Vehicle;
import com.cttorentsystem.ottorentbackend.exception.ResourceNotFoundException;
import com.cttorentsystem.ottorentbackend.mapper.VehicleMapper;
import com.cttorentsystem.ottorentbackend.repositorys.VehicleReporsitory;
import com.cttorentsystem.ottorentbackend.service.EmailService;
import com.cttorentsystem.ottorentbackend.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.cttorentsystem.ottorentbackend.mapper.VehicleMapper.mapToPhotoList;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private VehicleReporsitory vehicleReporsitory;

    private final EmailService emailService;

    @Override
    public VehicleDto createVehicle(VehicleDto vehicleDto) {

        Vehicle vehicle = VehicleMapper.mapToVehicle(vehicleDto);

       Vehicle saveVehicle = vehicleReporsitory.save(vehicle);

        return VehicleMapper.mapToVehicleDto(saveVehicle);

    }

    @Override
    public VehicleDto getVehicleById(Long vehicleId) {

        Vehicle vehicle =  vehicleReporsitory.findById(vehicleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vehicle not found with id : " + vehicleId));
        return  VehicleMapper.mapToVehicleDto(vehicle);
    }

    @Override
    public List<VehicleDto> getAllVehicles() {

        List<Vehicle> vehicleList = vehicleReporsitory.findAll();

        return vehicleList.stream().map(VehicleMapper::mapToVehicleDto).toList();

    }

    @Override
    public VehicleDto updateVehicle(VehicleDto vehicleDto, Long vehicleId) {

        Vehicle vehicle = vehicleReporsitory.findById(vehicleId).orElseThrow(() ->
                new ResourceNotFoundException("Vehicle not found with id : " + vehicleId));

        vehicle.setChassisNumber(vehicleDto.getChassisNumber());
        vehicle.setRegistrationNo(vehicleDto.getRegistrationNo());
        vehicle.setEngineNo(vehicleDto.getEngineNo());
        vehicle.setVehiclePrice(vehicleDto.getVehiclePrice());
        vehicle.setVehicleState(vehicleDto.getVehicleState());
        vehicle.setCompanyName(vehicleDto.getCompanyName());
        vehicle.setNumberOfDoors(vehicleDto.getNumberOfDoors());
        vehicle.setColor(vehicleDto.getColor());
        vehicle.setSeatingCapacity(vehicleDto.getSeatingCapacity());
        vehicle.setCondition(vehicleDto.getCondition());
        vehicle.setDimensions(VehicleMapper.mapToDimensions(vehicleDto.getDimensions()));
        vehicle.setCylinderCapacity(vehicleDto.getCylinderCapacity());
        vehicle.setFuelType(vehicleDto.getFuelType());
        vehicle.setManufacturedCountry(vehicleDto.getManufacturedCountry());
        vehicle.setAssembled(vehicleDto.isAssembled());
        vehicle.setVehicleType(vehicleDto.getVehicleType());
        vehicle.setBrand(vehicleDto.getBrand());
        vehicle.setStyle(vehicleDto.getStyle());
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setLicenses(vehicleDto.getLicenses());
        vehicle.setManufacturedYear(vehicleDto.getManufacturedYear());
        vehicle.setAlbum(mapToPhotoList(vehicleDto.getAlbumUrls()));
        vehicle.setServiceDetails(vehicleDto.getServiceDetails());
        vehicle.setInsuranceDetails(vehicleDto.getInsuranceDetails());


        Vehicle upDaterdVehicle = vehicleReporsitory.save(vehicle);

        return VehicleMapper.mapToVehicleDto(upDaterdVehicle);
    }


    @Override
     public VehicleDto deleteVehicle(Long vehicleId) {

        Vehicle vehicle = vehicleReporsitory.findById(vehicleId).orElseThrow(() ->
                new ResourceNotFoundException("Vehicle not found with id : " + vehicleId));
        vehicleReporsitory.delete(vehicle);
        return VehicleMapper.mapToVehicleDto(vehicle);
     }

     @Override
    public List<VehicleDto> suggestVehicle(Vehicle.VehicleType vehicleType, Vehicle.FuelType fuelType, int seatingCapacity) {


        List<Vehicle> matchingVehicles = vehicleReporsitory.findBySuggestVehicle(seatingCapacity, fuelType, vehicleType);
        if (matchingVehicles.isEmpty()) {
            throw new ResourceNotFoundException("No vehicles found with the provided criteria.");
        }
        return matchingVehicles.stream().map(VehicleMapper::mapToVehicleDto).toList();


    }

    @Override
    @Scheduled(cron = "0 */5 * * * *")  // Runs at midnight every day
    public void checkNextServiceDateAndNotifyOwner() {

        System.out.println("funtion is runing good ");

        List<Vehicle> vehicles = vehicleReporsitory.findAll();
        LocalDate currentDate = LocalDate.now();


        for (Vehicle vehicle : vehicles) {
            for (ServiceDetails serviceDetail : vehicle.getServiceDetails()) {
                LocalDate nextServiceDate = serviceDetail.getNextServiceDate();
                // Calculate the time difference

                long daysUntilNextService = java.time.Duration.between(currentDate, nextServiceDate).toDays();
                // Check if the next service date is within 7 days or behind the current date

                if (daysUntilNextService >= 0 && daysUntilNextService <= 7) {
                        // Send message to owner
                        String subject = "Service for Warning for vehicle: " + vehicle.getBrand() ;

                    EmailController ownerEmailController = new  EmailController("Admin-vehicleUpdate");
                    String emailBody = ownerEmailController.generateEmailBodyforVehicleUpdate(vehicle);
                    emailService.sendEmail("salindalakshan99@gmail.com", subject, emailBody);

                }
            }
        }

    }


}
