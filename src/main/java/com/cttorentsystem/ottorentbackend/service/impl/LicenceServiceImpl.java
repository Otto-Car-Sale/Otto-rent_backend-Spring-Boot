package com.cttorentsystem.ottorentbackend.service.impl;

import com.cttorentsystem.ottorentbackend.controllers.EmailController;
import com.cttorentsystem.ottorentbackend.dtos.LicenceDto;
import com.cttorentsystem.ottorentbackend.entity.Licence;
import com.cttorentsystem.ottorentbackend.entity.ServiceDetails;
import com.cttorentsystem.ottorentbackend.entity.Vehicle;
import com.cttorentsystem.ottorentbackend.mapper.LicenceMapper;
import com.cttorentsystem.ottorentbackend.repositorys.LicenceRepository;
import com.cttorentsystem.ottorentbackend.repositorys.VehicleReporsitory;
import com.cttorentsystem.ottorentbackend.service.EmailService;
import com.cttorentsystem.ottorentbackend.service.LicenceService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
@AllArgsConstructor
public class LicenceServiceImpl implements LicenceService {

    private LicenceRepository licenceRepository;
    private final EmailService emailService;
    private VehicleReporsitory vehicleReporsitory;

    @Override
    public LicenceDto createLicence(LicenceDto licenceDto) {

        Licence updateLicence = LicenceMapper.mapToLicence( licenceDto);

        Licence saveLicence = licenceRepository.save(updateLicence);

        return LicenceMapper.mapToLicenceDto(saveLicence);


    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")  // Runs every minute //0 0 0 * * * for every day
    public void triggerLicenceExpiryDateDate() {
        System.out.println("Function is running...");

        List<Vehicle> vehicles = vehicleReporsitory.findAll();
        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate);

        System.out.println(vehicles);
        for (Vehicle vehicle : vehicles) {

            List<Licence> licenceList = vehicle.getLicenses();

            if (licenceList != null) {
                System.out.println("Service details found for vehicle: " + vehicle.getBrand());

                for (Licence licence : licenceList) {
                    System.out.println("Processing service details for vehicle: " + licence.getExpiryDate());
                    LocalDate nextServiceDate = licence.getExpiryDate();

                    // Calculate the time difference in days
                    long daysUntilLicenceExpiry = ChronoUnit.DAYS.between(currentDate, nextServiceDate);


                    // Check if the next service date is within 7 days or behind the current date
                    if (daysUntilLicenceExpiry >= 0 && daysUntilLicenceExpiry <= 7) {
                        // Send message to owner
                        String subject = "Licence update Warning for vehicle: " + vehicle.getRegistrationNo();
                        String vehicleStatus = "Licence will expire within 7 days  for vehicle: " + vehicle.getRegistrationNo();
                        EmailController ownerEmailController = new EmailController("Admin-vehicleUpdate");
                        String emailBody = ownerEmailController.generateEmailBodyforVehicleUpdate(vehicle,vehicleStatus);
                        emailService.sendEmail("salindalakshan99@gmail.com", subject, emailBody);
                    }
                }
            } else {
                System.out.println("No service details found for vehicle: " + vehicle.getBrand());
            }
        }
    }
}
