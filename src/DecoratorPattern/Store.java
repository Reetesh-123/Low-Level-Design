package DecoratorPattern;

import DecoratorPattern.BasePizza.BasePizza;
import DecoratorPattern.BasePizza.VegDelight;
import DecoratorPattern.Toppings.ExtraCheese;
import DecoratorPattern.Toppings.Mushroom;

public class Store {
    public static void main(String[] args) {
        
        BasePizza pizza = new ExtraCheese( new Mushroom( new VegDelight()));
        System.out.println(pizza.cost());
    }
}
