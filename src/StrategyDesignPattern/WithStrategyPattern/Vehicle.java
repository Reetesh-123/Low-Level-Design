package StrategyDesignPattern.WithStrategyPattern;

import StrategyDesignPattern.WithStrategyPattern.Strategy.DriveStrategy;

public class Vehicle {
    DriveStrategy obj;

    Vehicle(DriveStrategy object) 
    {
        this.obj=object;
    }
    void drive()
    {
        obj.drive();
    }
}
