package com.cttorentsystem.ottorentbackend.mapper;

import com.cttorentsystem.ottorentbackend.dtos.VehicleDto;
import com.cttorentsystem.ottorentbackend.entity.Vehicle;

import java.util.List;
import java.util.stream.Collectors;

public class VehicleMapper {

    public static VehicleDto mapToVehicleDto(Vehicle vehicle) {
        VehicleDto dto = new VehicleDto();

        dto.setVehicleId(vehicle.getVehicleId());
        dto.setRegistrationNo(vehicle.getRegistrationNo());
        dto.setVehiclePrice(vehicle.getVehiclePrice());
        dto.setChassisNumber(vehicle.getChassisNumber());
        dto.setEngineNo(vehicle.getEngineNo());
        dto.setVehicleState(vehicle.getVehicleState());
        dto.setCompanyName(vehicle.getCompanyName());
        dto.setNumberOfDoors(vehicle.getNumberOfDoors());
        dto.setColor(vehicle.getColor());
        dto.setSeatingCapacity(vehicle.getSeatingCapacity());
        dto.setCondition(vehicle.getCondition());
        dto.setDimensions(mapToDimensionsDto(vehicle.getDimensions()));
        dto.setCylinderCapacity(vehicle.getCylinderCapacity());
        dto.setFuelType(vehicle.getFuelType());
        dto.setManufacturedCountry(vehicle.getManufacturedCountry());
        dto.setAssembled(vehicle.isAssembled());
        dto.setVehicleType(vehicle.getVehicleType());
        dto.setBrand(vehicle.getBrand());
        dto.setStyle(vehicle.getStyle());
        dto.setModel(vehicle.getModel());
        dto.setLicenses(vehicle.getLicenses());
        dto.setManufacturedYear(vehicle.getManufacturedYear());
        dto.setAlbumUrls(vehicle.getAlbum().stream().map(photo -> photo.getPhotoURL()).collect(Collectors.toList()));
        dto.setServiceDetails(vehicle.getServiceDetails());
        dto.setInsuranceDetails(vehicle.getInsuranceDetails());
        return dto;
    }

    private static VehicleDto.Dimensions mapToDimensionsDto(Vehicle.Dimensions dimensions) {
        if (dimensions == null) {
            return null;
        }
        return new VehicleDto.Dimensions(dimensions.getLength(), dimensions.getHeight(), dimensions.getWidth());
    }




    public static Vehicle mapToVehicle(VehicleDto dto) {
        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleId(dto.getVehicleId());
        vehicle.setRegistrationNo(dto.getRegistrationNo());
        vehicle.setVehiclePrice(dto.getVehiclePrice());
        vehicle.setChassisNumber(dto.getChassisNumber());
        vehicle.setEngineNo(dto.getEngineNo());
        vehicle.setVehicleState(dto.getVehicleState());
        vehicle.setCompanyName(dto.getCompanyName());
        vehicle.setNumberOfDoors(dto.getNumberOfDoors());
        vehicle.setColor(dto.getColor());
        vehicle.setSeatingCapacity(dto.getSeatingCapacity());
        vehicle.setCondition(dto.getCondition());
        vehicle.setDimensions(mapToDimensions(dto.getDimensions()));
        vehicle.setCylinderCapacity(dto.getCylinderCapacity());
        vehicle.setFuelType(dto.getFuelType());
        vehicle.setManufacturedCountry(dto.getManufacturedCountry());
        vehicle.setAssembled(dto.isAssembled());
        vehicle.setVehicleType(dto.getVehicleType());
        vehicle.setBrand(dto.getBrand());
        vehicle.setStyle(dto.getStyle());
        vehicle.setModel(dto.getModel());
        vehicle.setManufacturedYear(dto.getManufacturedYear());
        vehicle.setAlbum(mapToPhotoList(dto.getAlbumUrls()));
        vehicle.setLicenses(dto.getLicenses());
        vehicle.setServiceDetails(dto.getServiceDetails());
        vehicle.setInsuranceDetails(dto.getInsuranceDetails());



        return vehicle;
    }

    public static Vehicle.Dimensions mapToDimensions(VehicleDto.Dimensions dimensions) {
        if (dimensions == null) {
            return null;
        }
        return new Vehicle.Dimensions(dimensions.getLength(), dimensions.getHeight(), dimensions.getWidth());
    }

    public static List<Vehicle.Photo> mapToPhotoList(List<String> albumUrls) {
        if (albumUrls == null) {
            System.out.println("albumUrls in empty " );
            return null;
        }
        return albumUrls.stream()
                .map(url -> {
                    Vehicle.Photo photo = new Vehicle.Photo();
                    photo.setPhotoURL(url);
                    return photo;
                })
                .collect(Collectors.toList());
    }
}
