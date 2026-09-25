package BehavioralDesignPatterns.StrategyDesignPattern.WithStrategyPattern;

import BehavioralDesignPatterns.StrategyDesignPattern.WithStrategyPattern.Strategy.NormalDeriveStrategy;

public class PassengerVehicle extends Vehicle{
    PassengerVehicle()
    {
        super(new NormalDeriveStrategy());
    }
}
