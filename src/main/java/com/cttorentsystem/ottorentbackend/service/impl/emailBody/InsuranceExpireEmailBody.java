package com.cttorentsystem.ottorentbackend.service.impl.emailBody;

import com.cttorentsystem.ottorentbackend.dtos.OrderDto;
import com.cttorentsystem.ottorentbackend.entity.InsuranceDetails;
import com.cttorentsystem.ottorentbackend.entity.ServiceDetails;
import com.cttorentsystem.ottorentbackend.entity.Vehicle;
import com.cttorentsystem.ottorentbackend.service.EmailBodyGenerator;

import java.util.List;

public class InsuranceExpireEmailBody implements EmailBodyGenerator {
    @Override
    public String generateEmailBody(OrderDto order) {
        return null;
    }

    @Override
    public String generateEmailBodyforVehicleUpdate(Vehicle vehicle, String vehicleStatus) {
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

        List<InsuranceDetails> insuranceDetailsList = vehicle.getInsuranceDetails();
        for (InsuranceDetails insuranceDetails : insuranceDetailsList) {

            emailBody.append("Insurance Company: ").append(insuranceDetails.getCompanyName()).append("\n");
            emailBody.append("Insurance Coverage Period: ").append(insuranceDetails.getCoveragePeriod()).append("\n");
            emailBody.append("Insurance Renewal Date: ").append(insuranceDetails.getRenewalDate()).append("\n\n");
            emailBody.append("Insurance Claim Process: ").append(insuranceDetails.getClaimProcess()).append("\n\n");
            emailBody.append("Insurance Coverage Type: ").append(insuranceDetails.getCoverageType()).append("\n\n");

        }

        emailBody.append("Thank you for your cooperation.");
        emailBody.append(" OTTO Car Sale \n\n");

        return emailBody.toString();
    }
}
