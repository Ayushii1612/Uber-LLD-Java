package com.uberlld.factory;

import com.uberlld.models.Driver;
import com.uberlld.models.Rider;
import com.uberlld.models.User;
import com.uberlld.models.Vehicle;

// Creational — Factory Pattern
// SRP — only responsible for creating User objects
public class UserFactory {

    public static Rider createRider(String id,
                                    String name,
                                    String phone) {
        return new Rider(id, name, phone);
    }

    public static Driver createDriver(String id,
                                      String name,
                                      String phone,
                                      Vehicle vehicle) {
        return new Driver(id, name, phone, vehicle);
    }
}