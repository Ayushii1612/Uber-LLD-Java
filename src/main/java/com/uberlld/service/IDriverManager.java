package com.uberlld.service;

import com.uberlld.enums.DriverStatus;
import com.uberlld.models.Driver;
import com.uberlld.models.Location;

// DIP — high-level modules depend on this abstraction
// ISP — focused interface, only driver management methods
public interface IDriverManager {
    void addDriver(Driver driver);
    Driver findNearestDriver(Location riderLocation);
    void updateDriverStatus(Driver driver, DriverStatus status);
}