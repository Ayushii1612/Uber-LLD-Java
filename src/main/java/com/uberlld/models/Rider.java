package com.uberlld.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rider extends User {

    // Composition — ride history owned by Rider
    private final List<Ride> rideHistory = new ArrayList<>();

    public Rider(String userId, String name, String phone) {
        super(userId, name, phone);
    }

    // Polymorphism — implementing abstract method
    @Override
    public String getRole() { return "RIDER"; }

    public void addRideToHistory(Ride ride) {
        rideHistory.add(ride);
    }

    public List<Ride> getRideHistory() {
        return Collections.unmodifiableList(rideHistory);
    }

    @Override
    public String toString() {
        return "Rider: " + name + " | Phone: " + phone;
    }
}