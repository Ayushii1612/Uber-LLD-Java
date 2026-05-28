package com.uberlld.factory;

import com.uberlld.enums.VehicleType;
import com.uberlld.models.Vehicle;

// Creational — Factory Pattern
// SRP — only responsible for creating Vehicle objects
public class VehicleFactory {

    public static Vehicle createVehicle(String id,
                                        String plate,
                                        String type,
                                        String model) {
        VehicleType vehicleType =
                VehicleType.valueOf(type.toUpperCase());
        return new Vehicle(id, plate, vehicleType, model);
    }
}