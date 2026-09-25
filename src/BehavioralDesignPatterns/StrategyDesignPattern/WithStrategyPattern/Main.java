package BehavioralDesignPatterns.StrategyDesignPattern.WithStrategyPattern;

import BehavioralDesignPatterns.StrategyDesignPattern.WithStrategyPattern.Strategy.SportsDriveStrategy;

public class Main {
    public static void main(String[] args) {

        System.out.println("Passenger vehicle:");
        Vehicle passengerVehicle = new PassengerVehicle();
        passengerVehicle.drive();

        System.out.println("Sports vehicle:");
        Vehicle sportsVehicle = new SportsVehicle();
        sportsVehicle.drive();

        System.out.println("Off-road vehicle:");
        Vehicle offRoadVehicle = new OffRoadVehicle();
        offRoadVehicle.drive();

        // Strategy can also be swapped on an existing vehicle at runtime.
        System.out.println("Passenger vehicle switched to sports strategy:");
        passengerVehicle.setDriveStrategy(new SportsDriveStrategy());
        passengerVehicle.drive();
    }
}
