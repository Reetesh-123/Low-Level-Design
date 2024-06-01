package DecoratorPattern.Toppings;

import DecoratorPattern.BasePizza.BasePizza;

public abstract class ToppingDecorator extends BasePizza {
    public abstract int cost();
}
