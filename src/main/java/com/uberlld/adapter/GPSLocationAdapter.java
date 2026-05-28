package com.uberlld.adapter;

import com.uberlld.models.Location;

// Simulates a third-party GPS system with different format
class ThirdPartyGPS {
    // Returns location as "lat,lng" string — different format
    public String fetchCoordinates(String userId) {
        // Simulated GPS data
        return "28.4595,77.0266";
    }
}

// Adapter — converts ThirdPartyGPS format to our Location
// Association — GPSLocationAdapter uses ThirdPartyGPS
public class GPSLocationAdapter implements LocationProvider {

    private final ThirdPartyGPS gps = new ThirdPartyGPS();

    @Override
    public Location getCurrentLocation(String userId) {
        String coords = gps.fetchCoordinates(userId);
        String[] parts = coords.split(",");
        return new Location(
                Double.parseDouble(parts[0]),
                Double.parseDouble(parts[1])
        );
    }
}