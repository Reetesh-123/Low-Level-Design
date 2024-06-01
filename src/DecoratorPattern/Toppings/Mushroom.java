package DecoratorPattern.Toppings;

import DecoratorPattern.BasePizza.BasePizza;

public class Mushroom extends ToppingDecorator {
    
    BasePizza pizza;
    
    public Mushroom(BasePizza pizza){
        this.pizza=pizza;
    }

    @Override
    public int cost(){
        return pizza.cost()+15;
    }
}
