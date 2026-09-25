# Builder Design Pattern — Class Diagram

`CarBuilder` is a **static nested class** inside `Car`. It collects parts step-by-step (with defaults for optional ones) and produces an immutable `Car` via `build()`.

```mermaid
classDiagram
    direction TB

    class Main {
      <<client>>
      +main() void
    }

    class Car {
      -String engine
      -int wheels
      -int seats
      -String color
      -boolean sunroof
      -boolean navigationSystem
      -Car(CarBuilder)
      +toString() String
    }

    class CarBuilder {
      <<static nested>>
      -String engine
      -int wheels
      -int seats
      -String color
      -boolean sunroof
      -boolean navigationSystem
      +setEngine(String) void
      +setWheels(int) void
      +setSeats(int) void
      +setColor(String) void
      +setSunroof(boolean) void
      +setNavigationSystem(boolean) void
      +build() Car
    }

    Main ..> CarBuilder : uses
    CarBuilder ..> Car : creates
    Car --> CarBuilder : reads fields in ctor
```

## Legend

| Arrow | Meaning |
|---|---|
| `..>` | dependency — uses / instantiates |
| `-->` | association — holds a reference to |

`CarBuilder` is nested inside `Car` (`Car.CarBuilder`) — not shown as a separate relation, just a Java nesting detail.
