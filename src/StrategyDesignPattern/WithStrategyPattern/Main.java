package StrategyDesignPattern.WithStrategyPattern;

public class Main {
    public static void main(String[] args) {
        
        // Vehicle vehicle = new SportsVehicle();
        // Vehicle vehicle = new PassengerVehicle();
           Vehicle vehicle = new OffRoadVehicle();
        vehicle.drive();
    }
}
