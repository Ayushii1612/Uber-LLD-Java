# Uber-LLD-Java
This project is a complete Low-Level Design implementation of a ride-sharing platform similar to Uber, built entirely in Java. The system simulates the backend core of a ride-sharing application, covering user creation, driver registration, ride lifecycle management, fare calculation, notifications, and concurrent access handling.

The design is based on strong OOP principles using inheritance, abstraction, encapsulation, and polymorphism. The abstract User class is extended by Rider and Driver, while interfaces like FareCalculator enable flexible strategy-based implementations. Relationships such as composition, aggregation, and association are accurately modeled across the system.

All SOLID principles are implemented in the architecture. Classes follow Single Responsibility, new pricing strategies can be added without modifying existing code, and dependencies rely on interfaces instead of concrete implementations.

The project also demonstrates multiple design patterns. Creational patterns include Factory and Singleton, Structural patterns include Decorator and Adapter, and Behavioral patterns include Strategy and Observer. These patterns make the system modular, scalable, and extensible.

Concurrency and thread safety are handled using ConcurrentHashMap, CopyOnWriteArrayList, synchronized methods, and thread-safe Singleton implementation. A multithreading demo proves the system can safely handle concurrent operations. The project also includes a simulated REST API layer through RideController, reflecting how a real Spring Boot backend would be structured.
# Features
* Abstract User model with Rider and Driver roles using inheritance and polymorphism
* Factory pattern for creating Users and Vehicles
* Thread-safe Singleton RideService with double-checked locking
* Nearest driver matching using coordinate-based distance calculation
* Complete ride lifecycle management with status tracking
* Strategy pattern for dynamic fare calculation and surge pricing
* Decorator pattern for applying discounts without changing base logic
* Adapter pattern for integrating third-party GPS services
* Observer pattern for real-time Rider and Driver notifications
* Dependency Inversion using IDriverManager interface
* Thread-safe collections with ConcurrentHashMap and CopyOnWriteArrayList
* Multithreading support with concurrent driver registration demo
* REST API simulation through RideController endpoints
* Ride history tracking and driver rating system
* Vehicle-based fare multipliers and driver status management
# Project Structure
```
UberLLD/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── uberlld/
│                   ├── enums/
│                   │   ├── VehicleType.java
│                   │   ├── DriverStatus.java
│                   │   └── RideStatus.java
│                   ├── models/
│                   │   ├── Location.java
│                   │   ├── Vehicle.java
│                   │   ├── User.java
│                   │   ├── Rider.java
│                   │   ├── Driver.java
│                   │   └── Ride.java
│                   ├── strategy/
│                   │   ├── FareCalculator.java
│                   │   ├── BasicFareCalculator.java
│                   │   └── SurgeFareCalculator.java
│                   ├── factory/
│                   │   ├── UserFactory.java
│                   │   └── VehicleFactory.java
│                   ├── observer/
│                   │   ├── RideObserver.java
│                   │   ├── RiderNotificationObserver.java
│                   │   └── DriverNotificationObserver.java
│                   ├── decorator/
│                   │   ├── FareCalculatorDecorator.java
│                   │   └── DiscountFareDecorator.java
│                   ├── adapter/
│                   │   ├── LocationProvider.java
│                   │   └── GPSLocationAdapter.java
│                   ├── service/
│                   │   ├── IDriverManager.java
│                   │   ├── DriverManager.java
│                   │   ├── RideService.java
│                   │   └── RideServiceSingleton.java
│                   ├── api/
│                   │   └── RideController.java
│                   └── Main.java
└── README.md
```
# Tech Stack
* Java 17+
* Object-Oriented Programming — Encapsulation, Abstraction, Inheritance, Polymorphism
* Class Relationships — Association, Aggregation, Composition
* SOLID Principles — SRP, OCP, LSP, ISP, DIP
* Creational Patterns — Singleton (double-checked locking), Factory Method
* Structural Patterns — Decorator, Adapter
* Behavioral Patterns — Strategy, Observer
* Concurrency — synchronized, ConcurrentHashMap, CopyOnWriteArrayList, Multithreading
* REST API Simulation — RideController with HTTP-style request and response mapping
* No external dependencies — pure Java standard library only

