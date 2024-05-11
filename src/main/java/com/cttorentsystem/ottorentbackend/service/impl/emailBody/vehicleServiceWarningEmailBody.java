package com.cttorentsystem.ottorentbackend.service.impl.emailBody;

import com.cttorentsystem.ottorentbackend.dtos.OrderDto;
import com.cttorentsystem.ottorentbackend.dtos.VehicleDto;
import com.cttorentsystem.ottorentbackend.entity.ServiceDetails;
import com.cttorentsystem.ottorentbackend.entity.Vehicle;
import com.cttorentsystem.ottorentbackend.service.EmailBodyGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class vehicleServiceWarningEmailBody implements EmailBodyGenerator {


    @Override
    public String generateEmailBody(OrderDto vehicle) {

        return null;
    }

    @Override
    public String generateEmailBodyforVehicleUpdate(Vehicle vehicle,String vehicleStatus) {
        StringBuilder emailBody = new StringBuilder();

        // Add vehicle information to the email body
        emailBody.append("Dear sir :\n");
        emailBody.append(" :\n").append(vehicleStatus).append("\n\n");
        emailBody.append("Vehicle Information:\n");
        emailBody.append("========================================").append("\n");
        emailBody.append("Vehicle ID: ").append(vehicle.getVehicleId()).append("\n");
        emailBody.append("Registration No: ").append(vehicle.getRegistrationNo()).append("\n");
        emailBody.append("Brand: ").append(vehicle.getBrand()).append("\n");
        emailBody.append("Model: ").append(vehicle.getModel()).append("\n");
        emailBody.append("==========================================").append("\n\n");
        emailBody.append("Next Service Details:\n");

        List<ServiceDetails> serviceDetailsList = vehicle.getServiceDetails();
        for (ServiceDetails serviceDetail : serviceDetailsList) {
            emailBody.append("Service Date: ").append(serviceDetail.getServiceDate()).append("\n");
            emailBody.append("Service Center: ").append(serviceDetail.getServiceCenterName()).append("\n");
            emailBody.append("Next Service Date: ").append(serviceDetail.getNextServiceDate()).append("\n");
            emailBody.append("Next Service Distance: ").append(serviceDetail.getNextServiceDistance()).append("\n\n");
        }

        emailBody.append("Thank you for your cooperation.");
        emailBody.append(" OTTO Car Sale \n\n");

        return emailBody.toString();
    }
}
