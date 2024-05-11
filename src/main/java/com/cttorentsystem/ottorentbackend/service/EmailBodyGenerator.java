package com.cttorentsystem.ottorentbackend.service;

import com.cttorentsystem.ottorentbackend.dtos.OrderDto;
import com.cttorentsystem.ottorentbackend.dtos.VehicleDto;
import com.cttorentsystem.ottorentbackend.entity.Order;
import com.cttorentsystem.ottorentbackend.entity.Vehicle;
import org.springframework.stereotype.Service;

@Service
public interface EmailBodyGenerator {

    String generateEmailBody(OrderDto order);

    String generateEmailBodyforVehicleUpdate(Vehicle vehicle,String vehicleStatus);
}
