package com.uberlld.strategy;

import com.uberlld.enums.VehicleType;
import com.uberlld.models.Location;

// Strategy Pattern — interface for all fare strategies
// ISP — small focused interface, single method
public interface FareCalculator {
    double calculate(Location source, Location destination,
                     VehicleType vehicleType);
}