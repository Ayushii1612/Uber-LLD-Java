package com.uberlld.models;

import com.uberlld.enums.RideStatus;
import java.util.UUID;

// Composition — Ride owns Rider + Driver references
public class Ride {

    private final String rideId;
    private final Rider rider;
    private final Driver driver;
    private final Location source;
    private final Location destination;
    private RideStatus status;
    private double fare;
    private long startTime;
    private long endTime;
    private int driverRating;
    private int riderRating;

    public Ride(Rider rider, Driver driver,
                Location source, Location destination) {
        this.rideId      = UUID.randomUUID().toString();
        this.rider       = rider;
        this.driver      = driver;
        this.source      = source;
        this.destination = destination;
        this.status      = RideStatus.REQUESTED;
    }

    public String     getRideId()        { return rideId; }
    public Rider      getRider()         { return rider; }
    public Driver     getDriver()        { return driver; }
    public Location   getSource()        { return source; }
    public Location   getDestination()   { return destination; }
    public RideStatus getStatus()        { return status; }
    public double     getFare()          { return fare; }
    public int        getDriverRating()  { return driverRating; }
    public int        getRiderRating()   { return riderRating; }

    public void setStatus(RideStatus status)  { this.status = status; }
    public void setFare(double fare)          { this.fare = fare; }
    public void setStartTime(long t)          { this.startTime = t; }
    public void setEndTime(long t)            { this.endTime = t; }
    public void setDriverRating(int r)        { this.driverRating = r; }
    public void setRiderRating(int r)         { this.riderRating = r; }

    @Override
    public String toString() {
        return  "Ride ID  : " + rideId +
                "\nRider    : " + rider.getName() +
                "\nDriver   : " + driver.getName() +
                "\nFrom     : " + source +
                "\nTo       : " + destination +
                "\nStatus   : " + status +
                "\nFare     : Rs." + String.format("%.2f", fare);
    }
}