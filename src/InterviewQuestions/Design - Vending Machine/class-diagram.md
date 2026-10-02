# Vending Machine — Class Diagram

`VendingMachine` (the `Context`) holds a reference to the current `VendingMachineState` and delegates every operation to it. Each concrete state (`NoCoinState`, `HasCoinState`, `DispenseState`, `SoldOutState`) implements only the operations valid for that state and returns the next state — `VendingMachine` never branches on "if state == X".

```mermaid
classDiagram
    direction TB

    class Main {
      <<caller>>
    }

    class VendingMachineState {
      <<abstract>>
      +insertCoin(VendingMachine vm, Coin coin) VendingMachineState
      +selectItem(VendingMachine vm, String item) VendingMachineState
      +dispense(VendingMachine vm) VendingMachineState
      +refill(VendingMachine vm, String item, int qty) VendingMachineState
      +returnCoin(VendingMachine vm) VendingMachineState
    }

    class NoCoinState {
      +insertCoin(VendingMachine vm, Coin coin) VendingMachineState
    }

    class HasCoinState {
      +insertCoin(VendingMachine vm, Coin coin) VendingMachineState
      +selectItem(VendingMachine vm, String item) VendingMachineState
      +returnCoin(VendingMachine vm) VendingMachineState
    }

    class DispenseState {
      +dispense(VendingMachine vm) VendingMachineState
    }

    class SoldOutState {
      +refill(VendingMachine vm, String item, int qty) VendingMachineState
    }

    class VendingMachine {
      -VendingMachineState vendingMachineState
      -Inventory inventory
      -Balance balance
      -String selectedItem
      +insertCoin(Coin coin) void
      +selectItem(String item) void
      +dispense() void
      +refill(String item, int qty) void
      +returnCoin() void
    }

    class Inventory {
      -Map~String, Integer~ price
      -Map~String, Integer~ quantity
      +addItem(String item, int price, int qty) void
      +isEmpty() boolean
    }

    class Balance {
      -int amount
      -Stack~Coin~ coins
      +addAmount(Coin coin) void
      +processCoin(int price) int
    }

    class Coin {
      <<enumeration>>
      FIFTY
      HUNDRED
      THOUSAND
      +getValue() int
    }

    Main ..> VendingMachine : uses
    VendingMachineState <|-- NoCoinState
    VendingMachineState <|-- HasCoinState
    VendingMachineState <|-- DispenseState
    VendingMachineState <|-- SoldOutState
    VendingMachine o--> VendingMachineState : vendingMachineState
    NoCoinState ..> HasCoinState : insertCoin()
    HasCoinState ..> DispenseState : selectItem()
    DispenseState ..> NoCoinState : dispense()
    DispenseState ..> SoldOutState : dispense() when empty
    SoldOutState ..> NoCoinState : refill()
    VendingMachine *--> Inventory : inventory
    VendingMachine *--> Balance : balance
    Balance ..> Coin : uses
    VendingMachineState ..> Coin : uses
```

## Legend

| Arrow | Meaning |
|---|---|
| `<\|--` | inheritance — extends an abstract class (parent listed first) |
| `o-->` | aggregation — `VendingMachine` holds a `VendingMachineState` reference, swapped on every transition |
| `*-->` | composition — `VendingMachine` owns and creates its `Inventory`/`Balance`, they don't outlive it |
| `..>` | dependency — uses, reads from, or transitions to |

The abstract `VendingMachineState` is named first in each inheritance relation (`VendingMachineState <|-- NoCoinState`) so Mermaid ranks it above its concrete states in the `direction TB` layout, matching standard UML.

## Scaling: many instances

A single `VendingMachine` with one mutable `vendingMachineState` field is fine for one physical machine, but a fleet of machines (or one shared transaction service handling many machines at once) needs each machine's state to be independent and looked up by id instead of hardcoded as one instance:

```mermaid
classDiagram
    direction TB

    class VendingMachineState {
      <<interface>>
      +insertCoin(VendingMachine vm, Coin coin) VendingMachineState
      +dispense(VendingMachine vm) VendingMachineState
    }

    class NoCoinState {
      <<singleton>>
    }

    class HasCoinState {
      <<singleton>>
    }

    class VendingMachine {
      -String machineId
      -VendingMachineState vendingMachineState
      +insertCoin(Coin coin) void
      +dispense() void
    }

    class VendingMachineRegistry {
      -Map~String, VendingMachine~ machinesById
      +getOrCreate(String machineId) VendingMachine
    }

    class Client {
      <<caller>>
    }

    VendingMachineState <|.. NoCoinState
    VendingMachineState <|.. HasCoinState
    VendingMachineRegistry o--> VendingMachine : manages many
    VendingMachine o--> VendingMachineState : vendingMachineState
    Client ..> VendingMachineRegistry : uses
```

Each `ConcreteState` becomes a stateless singleton (safe to share since it holds no per-machine data — all machine-specific data lives on `VendingMachine`/`Inventory`/`Balance`), and `VendingMachineRegistry` looks up or creates the right `VendingMachine` by `machineId` instead of the program holding one hardcoded instance.
