package com.uberlld.strategy;

import com.uberlld.enums.VehicleType;
import com.uberlld.models.Location;

// Open/Closed — new strategy, zero changes to existing code
public class BasicFareCalculator implements FareCalculator {

    private static final double BASE_FARE   = 20.0;
    private static final double PER_KM_RATE = 10.0;

    @Override
    public double calculate(Location source, Location destination,
                            VehicleType vehicleType) {
        double distance = source.distanceTo(destination);
        double fare     = BASE_FARE + (distance * PER_KM_RATE);

        switch (vehicleType) {
            case BIKE:  return fare * 0.8;
            case AUTO:  return fare * 1.0;
            case SEDAN: return fare * 1.3;
            case SUV:   return fare * 1.6;
            default:    return fare;
        }
    }
}