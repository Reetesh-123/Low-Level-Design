# Strategy Design Pattern — Class Diagram

## Without Strategy Pattern

Each subclass overrides `derive()`, so the same behaviour is duplicated (`SportsVehicle` and `OffroadVehicle` hold identical code).

```mermaid
classDiagram
    direction TB

    class Vehicle {
      +derive() void
    }
    class Passenger {
      +derive() void
    }
    class SportsVehicle {
      +derive() void
    }
    class OffroadVehicle {
      +derive() void
    }

    Vehicle <|-- Passenger
    Vehicle <|-- SportsVehicle
    Vehicle <|-- OffroadVehicle
```

## With Strategy Pattern

Driving behaviour is pulled out into `DriveStrategy`. `Vehicle` holds a strategy and delegates `drive()` to it; each subclass only picks a strategy in its constructor, so `SportsVehicle` and `OffRoadVehicle` share `SportsDriveStrategy` instead of copying code.

```mermaid
classDiagram
    direction TB

    class Main {
      <<client>>
      +main() void
    }

    class Vehicle {
      ~DriveStrategy obj
      ~Vehicle(object)
      ~drive() void
      ~setDriveStrategy(object) void
    }

    class PassengerVehicle {
      ~PassengerVehicle()
    }
    class SportsVehicle {
      ~SportsVehicle()
    }
    class OffRoadVehicle {
      ~OffRoadVehicle()
    }

    class DriveStrategy {
      <<interface>>
      +drive() void
    }
    class NormalDeriveStrategy {
      +drive() void
    }
    class SportsDriveStrategy {
      +drive() void
    }

    Main ..> Vehicle : uses

    Vehicle <|-- PassengerVehicle
    Vehicle <|-- SportsVehicle
    Vehicle <|-- OffRoadVehicle

    Vehicle o--> DriveStrategy : has-a
    DriveStrategy <|.. NormalDeriveStrategy : implements
    DriveStrategy <|.. SportsDriveStrategy : implements

    PassengerVehicle ..> NormalDeriveStrategy : injects
    SportsVehicle ..> SportsDriveStrategy : injects
    OffRoadVehicle ..> SportsDriveStrategy : injects
```

## Legend

| Arrow | Meaning |
|---|---|
| `..>` | dependency — uses / passes to `super(...)` |
| `<\|--` | inheritance — extends class |
| `<\|..` | realization — class implements interface |
| `o-->` | aggregation — vehicle holds its drive strategy |
