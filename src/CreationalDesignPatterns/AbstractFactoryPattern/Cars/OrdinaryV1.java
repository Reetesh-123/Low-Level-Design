package CreationalDesignPatterns.AbstractFactoryPattern.Cars;

import CreationalDesignPatterns.AbstractFactoryPattern.Vehicle;

public class OrdinaryV1 implements Vehicle {
    public void average(){
        System.out.println("30 km/h");
    }
}
