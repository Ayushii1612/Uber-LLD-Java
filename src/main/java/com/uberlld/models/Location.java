package com.uberlld.models;

// Encapsulation — all fields private, accessed via getters
public class Location {

    private final double latitude;
    private final double longitude;

    public Location(double latitude, double longitude) {
        this.latitude  = latitude;
        this.longitude = longitude;
    }

    public double distanceTo(Location other) {
        double dx = this.latitude  - other.latitude;
        double dy = this.longitude - other.longitude;
        return Math.sqrt(dx * dx + dy * dy) * 111;
    }

    public double getLatitude()  { return latitude; }
    public double getLongitude() { return longitude; }

    @Override
    public String toString() {
        return "(" + latitude + ", " + longitude + ")";
    }
}