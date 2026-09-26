# Bridge Design Pattern

Structural pattern: decouples an abstraction from its implementation so the two can vary independently, by having the abstraction hold a reference to an implementor interface instead of implementing behavior directly. See [class-diagram.md](./class-diagram.md).

## What it does

- `LivingThings` (abstraction) delegates the actual breathing mechanics to a `BreatheProcessImplementor` it holds, rather than implementing `breatheProcess()` itself.
- `Dog`/`Fish` (refined abstractions) each forward to whichever `BreatheProcessImplementor` they were constructed with.
- `LandBreatheProcess`/`WaterBreatheProcess` (concrete implementors) hold the actual breathing steps.
- Any `LivingThings` subclass can be paired with any `BreatheProcessImplementor` at construction time — e.g. nothing stops wiring a `Dog` with `WaterBreatheProcess` if that made sense.

## When to use

- Both an abstraction and its implementation are expected to grow independent hierarchies of variants (e.g. new animal types, new breathing mechanisms).
- You want to avoid a combinatorial explosion of subclasses (`DogLandBreather`, `DogWaterBreather`, `FishLandBreather`, ...) for every abstraction × implementation pairing.
- You want to swap implementations at runtime without touching the abstraction's code.

## When NOT to use

- Only one implementation will ever exist, or the abstraction and implementation don't vary independently — a simple hierarchy or a strategy field is enough.
- The indirection (interface + composition) doesn't earn its keep for a small, stable set of combinations.

## Roles (GoF)

| Role | Class | Notes |
|---|---|---|
| **Abstraction** | `LivingThings` | Holds a `BreatheProcessImplementor`, delegates `breatheProcess()` to it |
| **Refined Abstraction** | `Dog`, `Fish` | Concrete subclasses of `LivingThings` |
| **Implementor** | `BreatheProcessImplementor` | Interface — `breatheProcess()` |
| **Concrete Implementor** | `LandBreatheProcess`, `WaterBreatheProcess` | Actual breathing step sequences |
| **Client** | `Main` | Constructs a `LivingThings` with the implementor it wants, then calls `breatheProcess()` |

## Flow

```
Client --new Dog(landBreatheProcess)--> Dog(LivingThings) --breatheProcess()--> LandBreatheProcess.breatheProcess()
Client --new Fish(waterBreatheProcess)--> Fish(LivingThings) --breatheProcess()--> WaterBreatheProcess.breatheProcess()
```

## Key points

- The implementor is injected through the constructor — easy to swap/mock, and any `LivingThings` subclass can pair with any `BreatheProcessImplementor`.
- `LivingThings.breatheProcess()` is declared `public abstract` so refined abstractions in other packages can override it — it must be at least as visible as the interface method it forwards to.
- Two independent hierarchies (animal types vs. breathing mechanisms) grow without cross-multiplying into subclasses for every combination.

## Bridge vs. look-alikes

| Pattern | Intent |
|---|---|
| **Bridge** | Decouple an abstraction from its implementation so both can vary independently |
| **Adapter** | Change an interface to one the client expects (usually applied after the fact, to existing incompatible code) |
| **Strategy** | Swap one algorithm/behavior at runtime; no separate abstraction hierarchy on the other side |
| **Decorator** | Same interface, adds behavior by wrapping, not decoupling two hierarchies |

## Real-world examples

- JDBC — `DriverManager`/`Connection` (abstraction) vs. vendor-specific driver implementations (implementor)
- AWT/Swing — `Component`/`Window` abstraction backed by platform-specific peer implementations
- SLF4J — logging API abstraction (`Logger`) bridged to Logback/Log4j/JUL implementations
- Shape-rendering frameworks — a `Shape` abstraction bridged to different rendering-API implementors (OpenGL, DirectX, software)

## Scaling this pattern

- One `LivingThings` instance per demo call works for a single flow. At interview scale, replace ad hoc construction with a `LivingThingsRegistry` keyed by session/context id — create-and-cache the abstraction+implementor pairing per key instead of constructing one at startup.
- If a `BreatheProcessImplementor` holds per-context state (e.g. a connection or buffer), it must resolve its collaborator per call via the registry rather than being bound once at construction, so multiple concurrent contexts don't share mutable state.
- If a concrete implementor mutates shared state (e.g. a shared resource pool), guard that mutation point with synchronization or a thread-safe collection — the abstraction itself doesn't need to be thread-safe if its implementor is.
- When the implementor side moves to a separate service (e.g. a remote breathing-simulation service), the abstraction's delegation call becomes a network call (HTTP/gRPC/message queue) instead of an in-process method call — the abstraction keeps the same simplified interface for the client, but now also owns timeouts/retries/error translation for that call.

## Packages

| Package | Contents |
|---|---|
| `Abstraction` | `LivingThings` (Abstraction), `Dog`, `Fish` (Refined Abstractions) |
| `Implementor` | `BreatheProcessImplementor` (Implementor), `LandBreatheProcess`, `WaterBreatheProcess` (Concrete Implementors) |
| (default) | `Main` — client demo |
