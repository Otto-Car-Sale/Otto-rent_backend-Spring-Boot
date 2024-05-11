package com.cttorentsystem.ottorentbackend.service.impl.emailBody;

import com.cttorentsystem.ottorentbackend.dtos.OrderDto;
import com.cttorentsystem.ottorentbackend.dtos.VehicleDto;
import com.cttorentsystem.ottorentbackend.entity.Order;
import com.cttorentsystem.ottorentbackend.entity.Vehicle;
import com.cttorentsystem.ottorentbackend.service.EmailBodyGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminEmailBody implements EmailBodyGenerator {
    @Override
    public String generateEmailBody(OrderDto order) {
        String emailBody = String.format(
                "Dear %s,%n%n" +
                        "We are pleased to inform you that a new vehicle order has been received. Here are the details:%n%n" +
                        "- Order ID: %d%n" +
                        "- Pickup Date: %s%n" +
                        "- Pickup Time: %s%n" +
                        "- Rental Dates: %s%n" +
                        "- Vehicle Details:%n" +
                        "  - Brand: %s%n" +
                        "  - Model: %s%n" +

                        "- Customer Details:%n" +
                        "  - Name: %s %s%n" +
                        "  - Email: %s%n" +
                        "  - Phone Number: %s%n" +
                        "- Destination: %s%n" +
                        "- Order Status: %s%n%n" +
                        "You can view more details and manage the order by clicking on the following link: %s%n%n" +
                        "Thank you for !%n%n" +
                        "Best regards,%n" +
                        "OTTorent Team",
                order.getCustomer().getFirstName(),
                order.getOrderId(),
                order.getPickupDate(),
                order.getPickupTime(),
                order.getRentalDates(),
                order.getVehicles().getBrand(),
                order.getVehicles().getModel(),
                order.getCustomer().getFirstName(),
                order.getCustomer().getLastName(),
                order.getCustomer().getEmail(),
                order.getCustomer().getPhoneNO(),
                order.getDestination(),
                order.getOrderStatus(),
                "Link to Order Details"
        );
        return emailBody;
    }

    @Override
    public String generateEmailBodyforVehicleUpdate(Vehicle vehicle,String vehicleStatus) {
        return null;
    }
}
