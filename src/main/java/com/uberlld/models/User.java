package com.uberlld.models;

// Abstraction — abstract base, hides internal complexity
// Inheritance — Rider and Driver extend this
public abstract class User {

    protected final String userId;
    protected final String name;
    protected final String phone;
    protected double rating;
    protected Location currentLocation;

    public User(String userId, String name, String phone) {
        this.userId = userId;
        this.name   = name;
        this.phone  = phone;
        this.rating = 5.0;
    }

    // Polymorphism — overridden in subclasses
    public abstract String getRole();

    public String   getUserId()            { return userId; }
    public String   getName()              { return name; }
    public String   getPhone()             { return phone; }
    public double   getRating()            { return rating; }
    public Location getCurrentLocation()   { return currentLocation; }

    public void setRating(double rating)              { this.rating = rating; }
    public void setCurrentLocation(Location location) { this.currentLocation = location; }
}