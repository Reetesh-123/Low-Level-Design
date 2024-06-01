package StrategyDesignPattern.WithStrategyPattern;

import StrategyDesignPattern.WithStrategyPattern.Strategy.NormalDeriveStrategy;

public class PassengerVehicle extends Vehicle{
    PassengerVehicle()
    {
        super(new NormalDeriveStrategy());
    }
}
