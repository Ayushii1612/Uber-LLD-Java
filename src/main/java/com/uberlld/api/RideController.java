package com.uberlld.api;

import com.uberlld.models.Location;
import com.uberlld.models.Ride;
import com.uberlld.models.Rider;
import com.uberlld.service.RideService;

// REST API Structure simulation
// In real world this would use Spring Boot @RestController
// SRP — only handles HTTP-style request/response mapping
public class RideController {

    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    // Simulates: POST /api/rides/request
    public String requestRide(Rider rider,
                              double srcLat, double srcLng,
                              double dstLat, double dstLng) {
        try {
            Location src  = new Location(srcLat, srcLng);
            Location dest = new Location(dstLat, dstLng);
            Ride ride = rideService.requestRide(
                    rider, src, dest);
            return "200 OK | Ride ID: " + ride.getRideId();
        } catch (Exception e) {
            return "503 Service Unavailable | "
                    + e.getMessage();
        }
    }

    // Simulates: POST /api/rides/{id}/accept
    public String acceptRide(Ride ride) {
        rideService.acceptRide(ride);
        return "200 OK | Ride Accepted";
    }

    // Simulates: POST /api/rides/{id}/start
    public String startRide(Ride ride) {
        rideService.startRide(ride);
        return "200 OK | Ride Started";
    }

    // Simulates: POST /api/rides/{id}/end
    public String endRide(Ride ride) {
        rideService.endRide(ride);
        return "200 OK | Ride Completed | Fare: Rs."
                + String.format("%.2f", ride.getFare());
    }

    // Simulates: POST /api/rides/{id}/cancel
    public String cancelRide(Ride ride) {
        rideService.cancelRide(ride);
        return "200 OK | Ride Cancelled";
    }

    // Simulates: POST /api/rides/{id}/rate
    public String rateDriver(Ride ride, int rating) {
        rideService.rateDriver(ride, rating);
        return "200 OK | Rating Submitted";
    }
}