package com.uberlld;

import com.uberlld.adapter.GPSLocationAdapter;
import com.uberlld.adapter.LocationProvider;
import com.uberlld.decorator.DiscountFareDecorator;
import com.uberlld.factory.UserFactory;
import com.uberlld.factory.VehicleFactory;
import com.uberlld.models.*;
import com.uberlld.observer.DriverNotificationObserver;
import com.uberlld.observer.RiderNotificationObserver;
import com.uberlld.service.DriverManager;
import com.uberlld.service.IDriverManager;
import com.uberlld.service.RideService;
import com.uberlld.service.RideServiceSingleton;
import com.uberlld.strategy.BasicFareCalculator;
import com.uberlld.strategy.FareCalculator;

public class Main {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("   UBER LLD - FULL SIMULATION  ");
        System.out.println("================================");

        // ── Factory Pattern — create objects via factory ──
        Vehicle sedan = VehicleFactory.createVehicle(
                "V1", "DL01AB1234", "SEDAN", "Swift Dzire");
        Vehicle bike  = VehicleFactory.createVehicle(
                "V2", "DL02CD5678", "BIKE", "Splendor");

        Driver driver1 = (Driver) UserFactory.createDriver(
                "D1", "Ramesh", "9111111111", sedan);
        Driver driver2 = (Driver) UserFactory.createDriver(
                "D2", "Suresh", "9222222222", bike);

        Rider rider = UserFactory.createRider(
                "R1", "Priya", "8888888888");

        // ── Adapter Pattern — GPS provides location ───────
        LocationProvider gps = new GPSLocationAdapter();
        Location driver1Location = gps.getCurrentLocation("D1");
        driver1.setCurrentLocation(driver1Location);
        driver2.setCurrentLocation(
                new Location(28.4700, 77.0300));
        rider.setCurrentLocation(
                new Location(28.4600, 77.0280));

        System.out.println("\n[Adapter] GPS Location for D1: "
                + driver1Location);
        System.out.println("[Polymorphism] Ramesh role: "
                + driver1.getRole());
        System.out.println("[Polymorphism] Priya role:  "
                + rider.getRole());

        // ── Strategy + Decorator Pattern ──────────────────
        FareCalculator basicCalc   = new BasicFareCalculator();
        FareCalculator discounted  =
                new DiscountFareDecorator(basicCalc, 10.0); // 10% off

        // ── Singleton Pattern ──────────────────────────────
        IDriverManager driverManager = new DriverManager();
        RideService rideService =
                RideServiceSingleton.getInstance(
                        driverManager, discounted);

        // ── Observer Pattern — register observers ──────────
        rideService.registerObserver(
                new RiderNotificationObserver());
        rideService.registerObserver(
                new DriverNotificationObserver());

        // ── Register drivers ───────────────────────────────
        System.out.println("\n--- Registering Drivers ---");
        driverManager.addDriver(driver1);
        driverManager.addDriver(driver2);

        // ── Full Ride Flow ─────────────────────────────────
        System.out.println("\n--- Starting Ride Flow ---");
        Location source      = new Location(28.4600, 77.0280);
        Location destination = new Location(28.5355, 77.3910);

        Ride ride = rideService.requestRide(
                rider, source, destination);

        rideService.acceptRide(ride);
        rideService.startRide(ride);
        rideService.endRide(ride);
        rideService.rateDriver(ride, 5);

        // ── Final Summary ──────────────────────────────────
        System.out.println("\n================================");
        System.out.println("RIDE SUMMARY");
        System.out.println("================================");
        System.out.println(ride);
        System.out.println("\nPriya ride history  : "
                + rider.getRideHistory().size());
        System.out.println("Ramesh trip history : "
                + driver1.getTripHistory().size());
        System.out.println("Ramesh status       : "
                + driver1.getStatus());

        // ── Thread Safety Demo ─────────────────────────────
        System.out.println(
                "\n--- Thread Safety Demo ---");
        Runnable registerTask = () -> {
            Vehicle suv = VehicleFactory.createVehicle(
                    "V3", "DL03EF9999", "SUV", "Innova");
            Driver d3 = UserFactory.createDriver(
                    "D3", "Thread-Driver",
                    "9000000000", suv);
            d3.setCurrentLocation(
                    new Location(28.4800, 77.0400));
            driverManager.addDriver(d3);
        };

        // Two threads registering drivers simultaneously
        Thread t1 = new Thread(registerTask);
        Thread t2 = new Thread(registerTask);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Drivers after threading: "
                + ((DriverManager) driverManager)
                .getAllDrivers().size());
    }
}