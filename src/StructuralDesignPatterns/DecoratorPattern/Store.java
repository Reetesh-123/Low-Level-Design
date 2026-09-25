package StructuralDesignPatterns.DecoratorPattern;

import StructuralDesignPatterns.DecoratorPattern.BasePizza.BasePizza;
import StructuralDesignPatterns.DecoratorPattern.BasePizza.VegDelight;
import StructuralDesignPatterns.DecoratorPattern.Toppings.ExtraCheese;
import StructuralDesignPatterns.DecoratorPattern.Toppings.Mushroom;

public class Store {
    public static void main(String[] args) {
        
        BasePizza pizza = new ExtraCheese( new Mushroom( new VegDelight()));
        System.out.println(pizza.cost());
    }
}
