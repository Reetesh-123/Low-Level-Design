package ConcreteClass;

import AbstractClass.Beverage;

public class Coffee extends Beverage{

    @Override
    public void brew(){
        IO.println("Brewing coffee...");
    }

    @Override
    public void addCondiments(){
        System.out.println("Adding sugar and milk...");
    }
}
