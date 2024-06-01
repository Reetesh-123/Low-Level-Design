package StrategyDesignPattern.WithStrategyPattern.Strategy;

public class NormalDeriveStrategy implements DriveStrategy {
    public void drive(){
        System.out.println("Normal drive capability");
    }
}   
