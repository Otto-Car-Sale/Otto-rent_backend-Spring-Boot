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
public class CustomerEmailBody implements EmailBodyGenerator {
    @Override
    public String generateEmailBody(OrderDto order) {
        String emailBody = String.format(
                "Subject: Order Confirmation - Your Vehicle Rental%n%n" +
                        "Dear %s,%n%n" +
                        "We are excited to confirm that your vehicle rental order has been successfully created. Here are the details:%n%n" +
                        "- Order ID: %d%n" +
                        "- Pickup Date: %s%n" +
                        "- Pickup Time: %s%n" +
                        "- Rental Dates: %s%n" +
                        "- Vehicle Details:%n" +
                        "  - Brand: %s%n" +
                        "  - Model: %s%n" +
                        "- Destination: %s%n" +
                        "- Order Status: %s%n%n" +
                        "Your order is currently being processed, and we will keep you updated on its status. If you have any questions or need assistance, feel free to contact us at [Your Contact Information].%n%n" +
                        "Thank you for choosing us for your vehicle rental needs!%n%n" +
                        "Best regards,%n" +
                        "[Your Company Name]",
                order.getCustomer().getFirstName(),
                order.getOrderId(),
                order.getPickupDate(),
                order.getPickupTime(),
                order.getRentalDates(),
                order.getVehicles().getBrand(),
                order.getVehicles().getModel(),
                order.getDestination(),
                order.getOrderStatus()
        );
        return emailBody;
    }

    @Override
    public String generateEmailBodyforVehicleUpdate(Vehicle vehicle,String vehicleStatus) {
        return null;
    }
}
