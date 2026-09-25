# Car Rental System — Class Diagram

`VehicleRentalSystem` holds stores and users. Each `Store` has a location, a vehicle inventory and its reservations. A `Reservation` links a `User` to a `Vehicle`, a `Bill` is raised for the reservation, and a `Payment` settles the bill.

```mermaid
classDiagram
    direction TB

    class Main {
      <<client>>
      +main() void
    }

    class VehicleRentalSystem {
      ~List~Store~ stores
      ~List~User~ users
      +VehicleRentalSystem(stores, users)
      +getStore(location) Store
    }

    class Store {
      ~int storeId
      ~VehicleInventoryManagment inventoryManagment
      ~Location location
      ~List~Reservation~ reservations
      +setVehicle(list) void
      +getVehicles() List~Vehicle~
      +createReservation(vehicle, user) Reservation
      +completeReseration() void
    }

    class Location {
      ~String address
      ~String city
      ~String state
      ~int pincode
      +Location(address, city, state, pincode)
    }

    class VehicleInventoryManagment {
      ~List~Vehicle~ vehicles
      +VehicleInventoryManagment(list)
      +getVehicles() List~Vehicle~
      +setVehicles(list) void
    }

    class User {
      ~int userId
      ~String name
      ~String licence
      +User(userId, name, licence)
    }

    class Reservation {
      ~int reservationId
      ~User user
      ~Vehicle vehicle
      ~Date bookedFrom
      ~Date bookedTo
      ~long fromTimeStamp
      ~long toTimeStamp
      ~ReservationStatus reservationStatus
      ~Location location
      ~ReservationType reservationType
      +createReserve(user, vehicle) int
    }

    class ReservationStatus {
      <<enumeration>>
      SCHEDULED
      INPROGRESS
      COMPLETED
      CANCELED
    }

    class ReservationType {
      <<enumeration>>
      HOURLY
      DAILY
    }

    class Bill {
      ~Reservation reservation
      ~boolean isPaid
      ~double amount
      +Bill(reservation, isPaid, amount)
      -computeBillAmount() double
    }

    class Payment {
      ~Bill bill
      +payBill(bill) void
    }

    class Vehicle {
      ~int vehicleID
      ~int vehicleNumber
      ~VehicleType vehicleType
      ~String companyName
      ~String modelName
      ~int kmDriven
      ~Date manufacturingDate
      ~int average
      ~int cc
      ~int dailyRentalCost
      ~int hourlyRentalCost
      ~int noOfSeat
      ~Status status
      +getVehicleID() int
      +setVehicleID(id) void
      +getVehicleType() VehicleType
      +setVehicleType(type) void
    }

    class Car
    class Bike

    class VehicleType {
      <<enumeration>>
      CAR
      BIKE
    }

    class Status {
      <<enumeration>>
      COMPLETED
      COLSED
      SCHEDULED
    }

    Main ..> VehicleRentalSystem : uses

    VehicleRentalSystem "1" o-- "*" Store
    VehicleRentalSystem "1" o-- "*" User

    Store "1" --> "1" Location
    Store "1" *-- "1" VehicleInventoryManagment
    Store "1" *-- "*" Reservation
    VehicleInventoryManagment "1" o-- "*" Vehicle

    Reservation --> User
    Reservation --> Vehicle
    Reservation --> Location
    Reservation --> ReservationStatus
    Reservation --> ReservationType

    Bill --> Reservation
    Payment ..> Bill : pays

    Vehicle <|-- Car
    Vehicle <|-- Bike
    Vehicle --> VehicleType
    Vehicle --> Status
```

> Note: in the current code `VehicleInventoryManagment` also `extends Vehicle`. That looks like a mistake (an inventory *has* vehicles, it *is not* one), so the diagram leaves it out.

## Legend

| Arrow | Meaning |
|---|---|
| `..>` | dependency — uses |
| `-->` | association — field reference |
| `o--` | aggregation — holds a collection it did not create |
| `*--` | composition — owned and created by the container |
| `<\|--` | inheritance — extends class |
