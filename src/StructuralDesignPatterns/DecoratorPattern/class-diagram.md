# Decorator Pattern — Class Diagram

A `ToppingDecorator` **is-a** `BasePizza` and **has-a** `BasePizza`, so toppings can wrap a pizza (or another topping) and add to its `cost()`.

```mermaid
classDiagram
    direction TB

    class Store {
      <<client>>
      +main() void
    }

    class BasePizza {
      <<abstract>>
      +cost() int
    }

    class Marghrita {
      +cost() int
    }

    class VegDelight {
      +cost() int
    }

    class ToppingDecorator {
      <<abstract>>
      +cost()* int
    }

    class ExtraCheese {
      ~BasePizza pizza
      +ExtraCheese(pizza)
      +cost() int
    }

    class Mushroom {
      ~BasePizza pizza
      +Mushroom(pizza)
      +cost() int
    }

    Store ..> BasePizza : uses

    BasePizza <|-- Marghrita
    BasePizza <|-- VegDelight
    BasePizza <|-- ToppingDecorator

    ToppingDecorator <|-- ExtraCheese
    ToppingDecorator <|-- Mushroom

    ExtraCheese o--> BasePizza : wraps
    Mushroom o--> BasePizza : wraps
```

Example from `Store`: `new ExtraCheese(new Mushroom(new VegDelight()))` → 200 + 15 + 10 = **225**.

## Legend

| Arrow | Meaning |
|---|---|
| `..>` | dependency — client uses the pizza |
| `<\|--` | inheritance — extends class |
| `o-->` | aggregation — decorator holds the wrapped pizza (injected via constructor) |
