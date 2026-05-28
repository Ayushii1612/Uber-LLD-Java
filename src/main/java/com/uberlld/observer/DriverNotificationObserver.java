package com.uberlld.observer;

import com.uberlld.models.Ride;

public class DriverNotificationObserver
        implements RideObserver {

    @Override
    public void onRideStatusChanged(Ride ride) {
        System.out.println(
                "  [Driver Notification] Hey " +
                        ride.getDriver().getName() +
                        "! Ride status updated: " + ride.getStatus());
    }
}