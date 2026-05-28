package com.uberlld.service;

import com.uberlld.enums.DriverStatus;
import com.uberlld.models.Driver;
import com.uberlld.models.Location;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

// Thread Safety — CopyOnWriteArrayList for concurrent access
// SRP — only manages drivers
public class DriverManager implements IDriverManager {

    // Thread-safe list for concurrent driver registration
    private final List<Driver> allDrivers =
            new CopyOnWriteArrayList<>();

    @Override
    public synchronized void addDriver(Driver driver) {
        allDrivers.add(driver);
        System.out.println("Driver registered: "
                + driver.getName());
    }

    // Thread-safe nearest driver search
    @Override
    public synchronized Driver findNearestDriver(
            Location riderLocation) {

        Optional<Driver> nearest = allDrivers.stream()
                .filter(d -> d.getStatus() == DriverStatus.AVAILABLE)
                .filter(d -> d.getCurrentLocation() != null)
                .min(Comparator.comparingDouble(d ->
                        d.getCurrentLocation()
                                .distanceTo(riderLocation)));

        return nearest.orElse(null);
    }

    @Override
    public synchronized void updateDriverStatus(
            Driver driver, DriverStatus status) {
        driver.setStatus(status);
    }

    public List<Driver> getAllDrivers() {
        return new ArrayList<>(allDrivers);
    }
}