package com.uberlld.observer;

import com.uberlld.models.Ride;

public class RiderNotificationObserver
        implements RideObserver {

    @Override
    public void onRideStatusChanged(Ride ride) {
        System.out.println(
                "  [Rider Notification] Hey " +
                        ride.getRider().getName() +
                        "! Your ride status: " + ride.getStatus());
    }
}