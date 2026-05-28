package com.uberlld.models;

import com.uberlld.enums.DriverStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Driver extends User {

    // Aggregation — Vehicle exists independently
    private final Vehicle vehicle;
    private DriverStatus status;

    // Composition — trip history owned by Driver
    private final List<Ride> tripHistory = new ArrayList<>();

    public Driver(String userId, String name,
                  String phone, Vehicle vehicle) {
        super(userId, name, phone);
        this.vehicle = vehicle;
        this.status  = DriverStatus.AVAILABLE;
    }

    // Polymorphism — implementing abstract method
    @Override
    public String getRole() { return "DRIVER"; }

    public Vehicle      getVehicle()     { return vehicle; }
    public DriverStatus getStatus()      { return status; }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public void addTripToHistory(Ride ride) {
        tripHistory.add(ride);
    }

    public List<Ride> getTripHistory() {
        return Collections.unmodifiableList(tripHistory);
    }

    @Override
    public String toString() {
        return "Driver: " + name +
                " | Vehicle: " + vehicle.getModel() +
                " | Status: " + status;
    }
}