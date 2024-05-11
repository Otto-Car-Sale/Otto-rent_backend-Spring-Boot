package com.cttorentsystem.ottorentbackend.service.impl;

import com.cttorentsystem.ottorentbackend.dtos.OrderDto;
import com.cttorentsystem.ottorentbackend.entity.Licence;
import com.cttorentsystem.ottorentbackend.entity.Vehicle;
import com.cttorentsystem.ottorentbackend.service.EmailBodyGenerator;
import com.cttorentsystem.ottorentbackend.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;


    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        System.out.println("Email sent to " + toEmail + " with subject: " + subject + " and body: " + body);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);

    }


    public static class LicenceTriggerEmailBody implements EmailBodyGenerator {
        @Override
        public String generateEmailBody(OrderDto order) {
            return null;
        }

        @Override
        public String generateEmailBodyforVehicleUpdate(Vehicle vehicle, String vehicleStatus) {
            StringBuilder emailBody = new StringBuilder();

            // Add vehicle information to the email body
            emailBody.append("Dear sir/madam,\n");
            emailBody.append(" :\n").append(vehicleStatus).append("\n\n");
            emailBody.append("Vehicle Information:\n");
            emailBody.append("========================================\n");
            emailBody.append("Vehicle ID: ").append(vehicle.getVehicleId()).append("\n");
            emailBody.append("Registration No: ").append(vehicle.getRegistrationNo()).append("\n");
            emailBody.append("Brand: ").append(vehicle.getBrand()).append("\n");
            emailBody.append("Model: ").append(vehicle.getModel()).append("\n");
            emailBody.append("========================================\n\n");
            emailBody.append("License Details:\n");

            List<Licence> licenceList = vehicle.getLicenses();
            for (Licence licence : licenceList) {
                emailBody.append("License Number: ").append(licence.getLicenseNumber()).append("\n");
                emailBody.append("Expiry Date: ").append(licence.getExpiryDate()).append("\n");
                emailBody.append("Issue Date: ").append(licence.getIssueDate()).append("\n");
                emailBody.append("Issue Province: ").append(licence.getIssueProvince()).append("\n\n");
            }

            emailBody.append("Thank you for your attention.\n\n");
            emailBody.append("Sincerely,\n");
            emailBody.append("OTTO Car Sale");

            return emailBody.toString();
        }


    }
}
