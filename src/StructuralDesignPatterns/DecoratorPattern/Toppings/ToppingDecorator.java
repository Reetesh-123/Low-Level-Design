package StructuralDesignPatterns.DecoratorPattern.Toppings;

import StructuralDesignPatterns.DecoratorPattern.BasePizza.BasePizza;

public abstract class ToppingDecorator extends BasePizza {
    public abstract int cost();
}
