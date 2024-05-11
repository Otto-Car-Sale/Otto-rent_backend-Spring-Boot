package com.cttorentsystem.ottorentbackend.service.impl;

import com.cttorentsystem.ottorentbackend.controllers.EmailController;
import com.cttorentsystem.ottorentbackend.dtos.ServiceDetailsDto;
import com.cttorentsystem.ottorentbackend.entity.ServiceDetails;
import com.cttorentsystem.ottorentbackend.entity.Vehicle;
import com.cttorentsystem.ottorentbackend.mapper.ServiceDetailsMapper;
import com.cttorentsystem.ottorentbackend.repositorys.ServiceDetailsReporsitory;
import com.cttorentsystem.ottorentbackend.repositorys.VehicleReporsitory;
import com.cttorentsystem.ottorentbackend.service.EmailService;
import com.cttorentsystem.ottorentbackend.service.ServiceDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
@AllArgsConstructor
public class ServiceDetailsServiceImpl implements ServiceDetailsService {

    private ServiceDetailsReporsitory serviceDetailsReporsitory;
    private final EmailService emailService;
    private VehicleReporsitory vehicleReporsitory;
    @Override
    public ServiceDetailsDto createServiceDetails(ServiceDetailsDto serviceDetailsDto) {

        ServiceDetails newserviceDetails = ServiceDetailsMapper.mapToServiceDetails(serviceDetailsDto);

        ServiceDetails savedServiceDetails = serviceDetailsReporsitory.save(newserviceDetails);
        return ServiceDetailsMapper.mapToServiceDetailsDto(savedServiceDetails);
    }

    @Override
    @Scheduled(cron = "0 10 0 * * *")  // Runs every minute
    public void triggerNextServiceDate() {
        System.out.println("Function is running...");

        List<Vehicle> vehicles = vehicleReporsitory.findAll();
        LocalDate currentDate = LocalDate.now();

        System.out.println(currentDate);

        System.out.println(vehicles);
        for (Vehicle vehicle : vehicles) {

            List<ServiceDetails> serviceDetail = vehicle.getServiceDetails();

            if (serviceDetail != null) {
                System.out.println("Service details found for vehicle: " + vehicle.getBrand());
                for (ServiceDetails serviceDetails : serviceDetail) {
                    System.out.println("Processing service details for vehicle: " + serviceDetails.getNextServiceDate());
                    LocalDate nextServiceDate = serviceDetails.getNextServiceDate();
                    // Calculate the time difference in days
                    long daysUntilNextService = ChronoUnit.DAYS.between(currentDate, nextServiceDate);
                    System.out.println(daysUntilNextService);
                    // Check if the next service date is within 7 days or behind the current date
                    if (daysUntilNextService >= 0 && daysUntilNextService <= 7) {
                        // Send message to owner
                        String subject = "Service for Warning for vehicle: " + vehicle.getBrand();
                        String vahicleStatus = "Next Service  data for vehicle: " + vehicle.getRegistrationNo();
                        EmailController ownerEmailController = new EmailController("Admin-vehicleUpdate");
                        String emailBody = ownerEmailController.generateEmailBodyforVehicleUpdate(vehicle,vahicleStatus);
                        emailService.sendEmail("salindalakshan99@gmail.com", subject, emailBody);
                    }
                }
            } else {
                System.out.println("No service details found for vehicle: " + vehicle.getBrand());
            }
        }
    }
}
