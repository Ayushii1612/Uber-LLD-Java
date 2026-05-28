package com.uberlld.service;

import com.uberlld.strategy.FareCalculator;

// Creational — Singleton Pattern (thread-safe)
// Ensures only one RideService instance exists
public class RideServiceSingleton {

    private static volatile RideService instance;

    private RideServiceSingleton() {}

    public static RideService getInstance(
            IDriverManager driverManager,
            FareCalculator fareCalculator) {

        if (instance == null) {
            synchronized (RideServiceSingleton.class) {
                if (instance == null) {
                    // Double-checked locking for thread safety
                    instance = new RideService(
                            driverManager, fareCalculator);
                }
            }
        }
        return instance;
    }
}