package com.cttorentsystem.ottorentbackend.service.impl;

import com.cttorentsystem.ottorentbackend.controllers.EmailController;
import com.cttorentsystem.ottorentbackend.dtos.InsuranceDetailsDto;
import com.cttorentsystem.ottorentbackend.entity.InsuranceDetails;
import com.cttorentsystem.ottorentbackend.entity.Licence;
import com.cttorentsystem.ottorentbackend.entity.Vehicle;
import com.cttorentsystem.ottorentbackend.mapper.InsuranceDetailsMapper;
import com.cttorentsystem.ottorentbackend.repositorys.InsuranceDetailsReporsitory;
import com.cttorentsystem.ottorentbackend.repositorys.VehicleReporsitory;
import com.cttorentsystem.ottorentbackend.service.EmailService;
import com.cttorentsystem.ottorentbackend.service.InsuranceDetailsService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class InsuranceDetailsServiceImpl implements InsuranceDetailsService {

    private InsuranceDetailsReporsitory insuranceDetailsReporsitory;
    private final EmailService emailService;
    private VehicleReporsitory vehicleReporsitory;
    @Override
    public InsuranceDetailsDto addInsuranceDetails(InsuranceDetailsDto insuranceDetailsDto) {

        InsuranceDetails insuranceDetails = InsuranceDetailsMapper.mapToInsuranceDetails(insuranceDetailsDto);

        InsuranceDetails savedInsuranceDetails = insuranceDetailsReporsitory.save(insuranceDetails);

        return InsuranceDetailsMapper.mapToInsuranceDetailsDto(savedInsuranceDetails);

    }

    @Override
    @Scheduled(cron = "0 30 0 * * *")  // Runs every minute //0 0 0 * * * for every day
    public void triggerInsuranceExpiryDateDate() {
        System.out.println("Function is running...");

        List<Vehicle> vehicles = vehicleReporsitory.findAll();
        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate);


        for (Vehicle vehicle : vehicles) {

            List<InsuranceDetails> insuranceDetails = vehicle.getInsuranceDetails();

            if (insuranceDetails != null) {
                System.out.println("Service details found for vehicle: " + vehicle.getBrand());

                for (InsuranceDetails insuranceDetail : insuranceDetails) {
                    System.out.println("Processing service details for vehicle: " + insuranceDetail.getRenewalDate());
                    LocalDate nextServiceDate = insuranceDetail.getRenewalDate();
                    // Calculate the time difference in days
                    long daysUntilLicenceExpiry = ChronoUnit.DAYS.between(currentDate, nextServiceDate);


                    // Check if the next service date is within 7 days or behind the current date
                    if (daysUntilLicenceExpiry >= 0 && daysUntilLicenceExpiry <= 7) {

                        // Send message to owner
                        String subject = "Insurance update Warning for vehicle: " + vehicle.getRegistrationNo();
                        String vehicleStatus = "Insurance will expire within 7 days  for vehicle: " + vehicle.getRegistrationNo();
                        EmailController ownerEmailController = new EmailController("Admin-vehicleUpdate");
                        String emailBody = ownerEmailController.generateEmailBodyforVehicleUpdate(vehicle, vehicleStatus);
                        emailService.sendEmail("salindalakshan99@gmail.com", subject, emailBody);
                    }
                }
            } else {
                System.out.println("No service details found for vehicle: " + vehicle.getBrand());
            }
        }
    }
}
