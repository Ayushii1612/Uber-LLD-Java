package com.uberlld.decorator;

import com.uberlld.enums.VehicleType;
import com.uberlld.models.Location;
import com.uberlld.strategy.FareCalculator;

// Decorator — wraps any FareCalculator and applies discount
public class DiscountFareDecorator
        extends FareCalculatorDecorator {

    private final double discountPercent;

    public DiscountFareDecorator(FareCalculator wrapped,
                                 double discountPercent) {
        super(wrapped);
        this.discountPercent = discountPercent;
    }

    @Override
    public double calculate(Location source, Location destination,
                            VehicleType vehicleType) {
        double originalFare = super.calculate(
                source, destination, vehicleType);
        double discount = originalFare * (discountPercent / 100);
        System.out.println("  [Decorator] Discount applied: Rs."
                + String.format("%.2f", discount));
        return originalFare - discount;
    }
}