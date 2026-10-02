# Vending Machine

Behavioral pattern: **State**. `VendingMachine` changes its allowed operations as its internal `VendingMachineState` changes, so the machine looks like it changes class as a transaction progresses. See [class-diagram.md](./class-diagram.md).

## What it does

- Extracts each lifecycle stage's behaviour into its own `ConcreteState` (`NoCoinState`, `HasCoinState`, `DispenseState`, `SoldOutState`) implementing the common `VendingMachineState` contract.
- `VendingMachine` (the `Context`) holds the *current* state and forwards every public call (`insertCoin`, `selectItem`, `dispense`, `refill`, `returnCoin`) to it instead of branching on a status flag.
- Each `ConcreteState` decides what the *next* state is (e.g. `HasCoinState.selectItem` returns a new `DispenseState`) — transition logic lives with the state, not in `VendingMachine`.
- `VendingMachineState`'s abstract base gives every operation a default "cannot do X in current state" body, and implements `refill()` once at the base since restocking is valid in *every* state and needs no per-state override.

## When to use

- An object's behaviour depends on a **finite set of states**, and it must change behaviour at runtime as its state changes.
- You have large conditional blocks (`if/else` or `switch` on a status field) scattered across methods, all checking the same state variable.
- Transitions between states follow clear rules and you want each state to own its own transition logic.

## When NOT to use

- Only two or three static behaviours exist and they never grow — a simple `if/else` or enum + switch is clearer and cheaper.
- States don't actually have distinct *behaviour*, only distinct *data* — that's just a data class, not this pattern.

## Roles (GoF)

| Role | Class | Notes |
|---|---|---|
| **State** | `VendingMachineState` | Abstract class; default bodies print "cannot do X"; `refill()` is implemented here directly since it's valid in every state |
| **ConcreteState** | `NoCoinState`, `HasCoinState`, `DispenseState`, `SoldOutState` | Each overrides only the operations valid for that stage, returns the next state |
| **Context** | `VendingMachine` | Holds `vendingMachineState`, `inventory`, `balance`, `selectedItem`; delegates every public method to the current state |
| **Client** | `Main` | Calls only `VendingMachine`'s public API, never touches a `VendingMachineState` directly |

## Flow

```
insertCoin(FIFTY)  -> NoCoinState.insertCoin(vm, coin)   -> Balance.addAmount(coin)               -> HasCoinState
selectItem("Coke") -> HasCoinState.selectItem(vm, item)  -> checks stock + price vs balance        -> DispenseState
dispense()         -> DispenseState.dispense(vm)         -> Inventory qty--, Balance.processCoin() -> NoCoinState (or SoldOutState if now empty)
returnCoin()        -> HasCoinState.returnCoin(vm)        -> Balance.processCoin(0) refunds in full -> NoCoinState
refill(item, qty)   -> VendingMachineState.refill(vm,...) -> Inventory quantity += qty              -> same state (or NoCoinState, if called from SoldOutState)
```

## Key points

- `refill` is the one operation that's **state-independent** — it's implemented directly on the abstract `VendingMachineState` instead of being duplicated as a no-op override in every concrete state. `SoldOutState` is the only state that overrides it, purely to transition back to `NoCoinState` once stock exists again.
- Items are identified by a plain `String` name across `Inventory`'s `price`/`quantity` maps — simple, but no type safety (a typo'd item name just silently misses `containsKey`). A `Product` value object (sku + name + price) would be more robust at scale.
- `selectedItem` is a single field on `VendingMachine`, set in `HasCoinState.selectItem` and read in `DispenseState.dispense`. This assumes exactly one in-flight selection at a time, which holds for one machine serving one customer serially — see [Extending the design](#extending-the-design) for why this breaks under concurrency.
- Invalid operations (e.g. `dispense()` while in `NoCoinState`) are handled by the base class's default body printing a message and returning `this` — not exceptions. Simple for a single-threaded CLI demo; a real system might want this to be an actual exception instead (see below).

## State vs. look-alikes

| Pattern | Intent |
|---|---|
| **State** | Object's behaviour changes with its internal state; states know how to transition to each other |
| **Strategy** | Client explicitly picks an interchangeable algorithm; strategies typically don't know about each other or transition on their own |
| **Command** | Encapsulates a *request* as an object (with undo/queueing); not about representing "what state am I in" |
| **Chain of Responsibility** | Passes a request along a chain until someone handles it; not about representing mutually exclusive states of one object |

## Real-world examples

- `java.util.concurrent.Future` / `Thread` — internal lifecycle states (NEW, RUNNABLE, TERMINATED, ...) drive different allowed operations
- TCP connection state machines (LISTEN, SYN_SENT, ESTABLISHED, CLOSED, ...)
- Media players — `Play`/`Pause`/`Stop` states change how the same button press behaves
- Order/workflow systems — `Order` behaving differently in `Placed`, `Shipped`, `Delivered`, `Cancelled` states

## Scaling this pattern

- **Many concurrent machines**: instead of one hardcoded `VendingMachine` instance, use a registry/factory keyed by `machineId` that looks up or creates the right `VendingMachine` (see `class-diagram.md`'s scaling section).
- **Stateless states as singletons**: `ConcreteState` classes hold no per-machine data, so they can be shared singletons across every `VendingMachine` rather than allocated fresh on every transition.
- **Thread-safety**: `VendingMachine`'s `vendingMachineState` field (and the `Inventory`/`Balance` it mutates) is shared state — under concurrent access it needs guarding (see below).
- **Distributed scale**: once a single process isn't enough (state must survive restarts, or machines report to a central fleet service), the in-memory `vendingMachineState` field becomes a persisted state column, and transitions are driven by messages from a broker/event bus instead of direct method calls.

## Extending the design

The current implementation is a single-machine, single-customer, in-memory CLI demo. Here's what each of these common LLD requirements would add, **without changing the current code**:

| Requirement | Current state | What would change |
|---|---|---|
| **Multiple products, different prices/quantities** | ✅ Already supported — `Inventory.price` / `Inventory.quantity` are keyed per item name via `addItem` | Nothing required; optionally promote the `String` item key to a `Product` value object for type safety |
| **Accept coins *and* notes of different denominations** | Partial — `Coin` enum (`FIFTY`/`HUNDRED`/`THOUSAND`) models denominations generically already | Introduce a `Denomination` interface (or a single enum covering both coins and notes) that `Balance` accepts instead of a `Coin`-specific type; `Balance.addAmount`/`processCoin` stay unchanged since they already just read `.getValue()` |
| **Dispense product and return change if necessary** | Partial — `Balance.processCoin(price)` computes `change = amount - price` as a flat integer | For *real* change-making (dispensing actual coins/notes back), the machine needs a till of available denominations and a change-making algorithm (greedy or DP) that picks which denominations to return and decrements the till accordingly, instead of returning a bare `int` |
| **Track available products and quantities** | ✅ Already supported — `Inventory.quantity`, checked in `HasCoinState.selectItem` and decremented in `DispenseState.dispense` | Nothing required |
| **Handle multiple transactions concurrently, consistent data** | ❌ Not supported — one `VendingMachine` instance mutates shared `Inventory`/`Balance` with no synchronization; concurrent `insertCoin`/`selectItem`/`dispense` calls can race | Either (a) treat each physical machine as single-threaded/one-customer-at-a-time and scale *horizontally* via the `VendingMachineRegistry` pattern in `class-diagram.md`, or (b) if multiple terminals share one central inventory service, guard `Inventory.quantity` mutations with a lock or optimistic CAS so two dispenses can't both decrement past zero |
| **Interface for restocking products and collecting money** | Partial — `refill(item, qty)` already exists as the restocking half | Collecting money doesn't exist yet: `Balance` currently represents *the current customer's* inserted amount, which gets cleared every transaction — the machine needs a separate running "till" field, plus a `collectMoney()` admin operation (state-independent, like `refill`, since an operator can empty the till regardless of what a customer is mid-doing) |
| **Handle exceptional scenarios (insufficient funds, out-of-stock)** | Partial — handled today via `System.out.println(...)` + returning the same/`this` state (soft validation, not exceptions) | Formalize as unchecked exceptions (`InsufficientBalanceException`, `OutOfStockException`) if the caller is a UI layer that needs to distinguish failure *types* programmatically rather than just reading console output; the current print-and-stay approach is also a legitimate choice for a single-threaded CLI/demo |

## Packages

| Package | Contents |
|---|---|
| `Enum` | `Coin` |
| `Machine` | `VendingMachine` (Context), `Inventory`, `Balance` |
| `States` | `VendingMachineState` (State) + `NoCoinState`, `HasCoinState`, `DispenseState`, `SoldOutState` (ConcreteState) |
