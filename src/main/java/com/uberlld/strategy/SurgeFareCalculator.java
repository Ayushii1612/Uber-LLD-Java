package com.uberlld.strategy;

import com.uberlld.enums.VehicleType;
import com.uberlld.models.Location;

// Open/Closed — added without touching BasicFareCalculator
public class SurgeFareCalculator implements FareCalculator {

    private final FareCalculator base;
    private final double surgeMultiplier;

    public SurgeFareCalculator(FareCalculator base,
                               double surgeMultiplier) {
        this.base             = base;
        this.surgeMultiplier  = surgeMultiplier;
    }

    @Override
    public double calculate(Location source, Location destination,
                            VehicleType vehicleType) {
        return base.calculate(source, destination, vehicleType)
                * surgeMultiplier;
    }
}