package com.uberlld.decorator;

import com.uberlld.enums.VehicleType;
import com.uberlld.models.Location;
import com.uberlld.strategy.FareCalculator;

// Structural — Decorator Pattern base
// Liskov Substitution — decorator IS-A FareCalculator
public abstract class FareCalculatorDecorator
        implements FareCalculator {

    protected final FareCalculator wrapped;

    public FareCalculatorDecorator(FareCalculator wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public double calculate(Location source, Location destination,
                            VehicleType vehicleType) {
        return wrapped.calculate(source, destination, vehicleType);
    }
}