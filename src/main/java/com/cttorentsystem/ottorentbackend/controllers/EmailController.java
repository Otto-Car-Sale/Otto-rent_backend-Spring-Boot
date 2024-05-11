package com.cttorentsystem.ottorentbackend.controllers;


import com.cttorentsystem.ottorentbackend.dtos.OrderDto;
import com.cttorentsystem.ottorentbackend.entity.Vehicle;
import com.cttorentsystem.ottorentbackend.service.EmailBodyGenerator;
import com.cttorentsystem.ottorentbackend.service.impl.emailBody.AdminEmailBody;
import com.cttorentsystem.ottorentbackend.service.impl.emailBody.CustomerEmailBody;

import com.cttorentsystem.ottorentbackend.service.impl.emailBody.vehicleServiceWarningEmailBody;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class EmailController {



    private EmailBodyGenerator emailBodyGenerator;



    public EmailController(String receiver) {

        if(receiver.equals("Admin-orderUpdate")){
            emailBodyGenerator = new AdminEmailBody();
        }else if (receiver.equals("Admin-vehicleUpdate")){
            emailBodyGenerator = new vehicleServiceWarningEmailBody();
        } else if(receiver.equals("Customer")){
            emailBodyGenerator = new CustomerEmailBody();
        }
    };


    public String generateEmailBody(OrderDto order) {

        String emailBody = emailBodyGenerator.generateEmailBody(order);
        return emailBody;
    }

    public String generateEmailBodyforVehicleUpdate(Vehicle vehicle,String vehicleStatus) {

        String emailBody = emailBodyGenerator.generateEmailBodyforVehicleUpdate(vehicle,vehicleStatus);
        return emailBody;
    }





}
