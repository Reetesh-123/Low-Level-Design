# Abstract Factory Pattern — Class Diagram

`VehicleFactory` is a factory of factories: it returns a concrete `VehicleFactoryInterface` (Luxury / Ordinary), which in turn creates the concrete `Vehicle` of that family.

```mermaid
classDiagram
    direction TB

    class Main {
      <<client>>
      +main() void
    }

    class VehicleFactory {
      +getVehicle(input) VehicleFactoryInterface
    }

    class VehicleFactoryInterface {
      <<interface>>
      +getVehicle(input) Vehicle
    }

    class LuxuryFactory {
      +getVehicle(input) Vehicle
    }

    class OrdinaryFactory {
      +getVehicle(input) Vehicle
    }

    class Vehicle {
      <<interface>>
      +average() void
    }

    class LuxuryV1 {
      +average() void
    }
    class LuxuryV2 {
      +average() void
    }
    class OrdinaryV1 {
      +average() void
    }
    class OrdinaryV2 {
      +average() void
    }

    Main ..> VehicleFactory : uses
    Main ..> VehicleFactoryInterface : uses
    VehicleFactory ..> VehicleFactoryInterface : creates

    VehicleFactoryInterface <|.. LuxuryFactory : implements
    VehicleFactoryInterface <|.. OrdinaryFactory : implements

    LuxuryFactory ..> LuxuryV1 : creates
    LuxuryFactory ..> LuxuryV2 : creates
    OrdinaryFactory ..> OrdinaryV1 : creates
    OrdinaryFactory ..> OrdinaryV2 : creates

    Vehicle <|.. LuxuryV1 : implements
    Vehicle <|.. LuxuryV2 : implements
    Vehicle <|.. OrdinaryV1 : implements
    Vehicle <|.. OrdinaryV2 : implements
```

## Legend

| Arrow | Meaning |
|---|---|
| `..>` | dependency — uses / instantiates |
| `<\|..` | realization — class implements interface |
