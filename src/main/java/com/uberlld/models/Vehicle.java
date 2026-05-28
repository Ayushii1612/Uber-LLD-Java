package com.uberlld.models;

import com.uberlld.enums.VehicleType;

// Aggregation — Vehicle exists independently of Driver
public class Vehicle {

    private final String vehicleId;
    private final String licensePlate;
    private final VehicleType type;
    private final String model;

    public Vehicle(String vehicleId, String licensePlate,
                   VehicleType type, String model) {
        this.vehicleId    = vehicleId;
        this.licensePlate = licensePlate;
        this.type         = type;
        this.model        = model;
    }

    public String      getVehicleId()    { return vehicleId; }
    public String      getLicensePlate() { return licensePlate; }
    public VehicleType getType()         { return type; }
    public String      getModel()        { return model; }

    @Override
    public String toString() {
        return model + " (" + type + ") - " + licensePlate;
    }
}