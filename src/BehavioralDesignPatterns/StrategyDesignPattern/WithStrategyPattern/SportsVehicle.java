package BehavioralDesignPatterns.StrategyDesignPattern.WithStrategyPattern;

import BehavioralDesignPatterns.StrategyDesignPattern.WithStrategyPattern.Strategy.SportsDriveStrategy;

public class SportsVehicle extends Vehicle {
    SportsVehicle()
    {
        super(new SportsDriveStrategy());
    }
}
