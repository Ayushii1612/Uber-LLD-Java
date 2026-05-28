package com.uberlld.adapter;

import com.uberlld.models.Location;

// Adapter Pattern — target interface our system expects
public interface LocationProvider {
    Location getCurrentLocation(String userId);
}