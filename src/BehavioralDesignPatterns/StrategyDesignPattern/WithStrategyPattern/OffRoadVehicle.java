package BehavioralDesignPatterns.StrategyDesignPattern.WithStrategyPattern;

import BehavioralDesignPatterns.StrategyDesignPattern.WithStrategyPattern.Strategy.SportsDriveStrategy;

public class OffRoadVehicle extends Vehicle{
    OffRoadVehicle(){
        super(new SportsDriveStrategy());
    }
}
