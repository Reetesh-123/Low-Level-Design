package ConcreteClass;

import AbstractClass.Beverage;

public class Tea extends Beverage{

    @Override
    protected void brew() {
        System.out.println("Steeping tea bag...");
    }
    @Override
    protected void addCondiments() {
        System.out.println("Adding lemon...");
    }

}
