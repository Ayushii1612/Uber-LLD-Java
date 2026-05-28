package com.uberlld.service;

import com.uberlld.enums.DriverStatus;
import com.uberlld.enums.RideStatus;
import com.uberlld.models.Driver;
import com.uberlld.models.Location;
import com.uberlld.models.Ride;
import com.uberlld.models.Rider;
import com.uberlld.observer.RideObserver;
import com.uberlld.strategy.FareCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// DIP — depends on IDriverManager and FareCalculator interfaces
// SRP — only manages ride lifecycle
// Observer — notifies all registered observers on status change
public class RideService {

    private final IDriverManager driverManager;
    private final FareCalculator fareCalculator;

    // Thread-safe map for concurrent ride management
    private final Map<String, Ride> activeRides =
            new ConcurrentHashMap<>();

    // Observer list
    private final List<RideObserver> observers =
            new ArrayList<>();

    // DIP — injected via constructor, not created inside
    public RideService(IDriverManager driverManager,
                       FareCalculator fareCalculator) {
        this.driverManager  = driverManager;
        this.fareCalculator = fareCalculator;
    }

    // Register observer
    public void registerObserver(RideObserver observer) {
        observers.add(observer);
    }

    // Notify all observers
    private void notifyObservers(Ride ride) {
        for (RideObserver observer : observers) {
            observer.onRideStatusChanged(ride);
        }
    }

    // STEP 1 — Request ride
    public synchronized Ride requestRide(
            Rider rider, Location source, Location destination) {

        System.out.println("\nRide requested by "
                + rider.getName());

        Driver driver = driverManager
                .findNearestDriver(source);

        if (driver == null) {
            throw new RuntimeException(
                    "No drivers available!");
        }

        Ride ride = new Ride(rider, driver, source, destination);
        activeRides.put(ride.getRideId(), ride);

        System.out.println("Nearest driver: "
                + driver.getName()
                + " | " + driver.getVehicle().getModel());

        notifyObservers(ride);
        return ride;
    }

    // STEP 2 — Accept ride
    public void acceptRide(Ride ride) {
        ride.setStatus(RideStatus.ACCEPTED);
        driverManager.updateDriverStatus(
                ride.getDriver(), DriverStatus.ON_TRIP);
        System.out.println("Ride accepted by "
                + ride.getDriver().getName());
        notifyObservers(ride);
    }

    // STEP 3 — Start ride
    public void startRide(Ride ride) {
        ride.setStatus(RideStatus.ONGOING);
        ride.setStartTime(System.currentTimeMillis());
        System.out.println("Ride started! "
                + ride.getSource() + " to "
                + ride.getDestination());
        notifyObservers(ride);
    }

    // STEP 4 — End ride
    public void endRide(Ride ride) {
        ride.setStatus(RideStatus.COMPLETED);
        ride.setEndTime(System.currentTimeMillis());

        double fare = fareCalculator.calculate(
                ride.getSource(),
                ride.getDestination(),
                ride.getDriver().getVehicle().getType()
        );
        ride.setFare(fare);

        ride.getRider().addRideToHistory(ride);
        ride.getDriver().addTripToHistory(ride);

        driverManager.updateDriverStatus(
                ride.getDriver(), DriverStatus.AVAILABLE);
        activeRides.remove(ride.getRideId());

        System.out.println("Ride completed! Fare: Rs."
                + String.format("%.2f", fare));
        notifyObservers(ride);
    }

    // STEP 5 — Cancel ride
    public void cancelRide(Ride ride) {
        ride.setStatus(RideStatus.CANCELLED);
        driverManager.updateDriverStatus(
                ride.getDriver(), DriverStatus.AVAILABLE);
        activeRides.remove(ride.getRideId());
        System.out.println("Ride cancelled.");
        notifyObservers(ride);
    }

    // STEP 6 — Rate driver
    public void rateDriver(Ride ride, int rating) {
        if (ride.getStatus() != RideStatus.COMPLETED) {
            System.out.println(
                    "Can only rate after ride completes.");
            return;
        }
        ride.setDriverRating(rating);
        System.out.println("Driver "
                + ride.getDriver().getName()
                + " rated " + rating + "/5");
    }

    public int getActiveRideCount() {
        return activeRides.size();
    }
}