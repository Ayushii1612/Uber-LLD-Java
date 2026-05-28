package com.uberlld.observer;

import com.uberlld.models.Ride;

// Observer Pattern — interface for all observers
// ISP — focused single-method interface
public interface RideObserver {
    void onRideStatusChanged(Ride ride);
}