# State Design Pattern — Class Diagram

`TrafficLight` (the `Context`) holds a reference to the current `TrafficLightState` and delegates to it. Each concrete state (`GreenLightState`, `YellowLightState`, `RedLightState`) implements the behaviour for one colour and decides what the next state should be — `TrafficLight`'s code never branches on "if colour == X".

```mermaid
classDiagram
    direction TB

    class TrafficLightState {
      <<interface>>
      +color() void
      +next() TrafficLightState
    }

    class GreenLightState {
      -TrafficLightState nextTrafficLightState
      -String colour
      +color() void
      +next() TrafficLightState
    }

    class YellowLightState {
      -TrafficLightState nextTrafficLightState
      -String colour
      +color() void
      +next() TrafficLightState
    }

    class RedLightState {
      -TrafficLightState nextTrafficLightState
      -String colour
      +color() void
      +next() TrafficLightState
    }

    class TrafficLight {
      -TrafficLightState trafficLightState
      +setTrafficLightState(TrafficLightState state) void
      +getTrafficLightNextState() TrafficLightState
      +moveToNextState() void
      +color() void
    }

    class Main {
      <<caller>>
    }

    TrafficLightState <|.. GreenLightState
    TrafficLightState <|.. YellowLightState
    TrafficLightState <|.. RedLightState
    TrafficLight o--> TrafficLightState : trafficLightState
    RedLightState ..> YellowLightState : next()
    YellowLightState ..> GreenLightState : next()
    Main ..> TrafficLight : uses
```

## Legend

| Arrow | Meaning |
|---|---|
| `<\|..` | realization — implements an interface (parent listed first) |
| `o-->` | aggregation — `TrafficLight` holds a `TrafficLightState` reference, doesn't own its lifecycle |
| `..>` | dependency — uses / transitions to |

Interfaces/base types are named first in each relation (`TrafficLightState <|.. GreenLightState`) so Mermaid ranks them above their implementers in the `direction TB` layout, matching standard UML.

## Scaling: many instances

A single `Context` object with one mutable `currentState` field is fine for a textbook example, but breaks down once many contexts need independent state at once (e.g. one traffic light per intersection, one order per customer). The fix is a registry/factory keyed by context id, and states that are stateless singletons shared across all contexts rather than newly allocated per transition:

```mermaid
classDiagram
    direction TB

    class State {
      <<interface>>
      +handle(Context context) void
      +next() State
    }

    class ConcreteStateA {
      <<singleton>>
      +handle(Context context) void
      +next() State
    }

    class ConcreteStateB {
      <<singleton>>
      +handle(Context context) void
      +next() State
    }

    class Context {
      -String contextId
      -State currentState
      +setState(State state) void
      +request() void
    }

    class ContextRegistry {
      -Map~String, Context~ contexts
      +getOrCreate(String contextId) Context
    }

    class Client {
      <<caller>>
    }

    State <|.. ConcreteStateA
    State <|.. ConcreteStateB
    ContextRegistry o--> Context : manages many
    Context o--> State : currentState
    Client ..> ContextRegistry : uses
```

Each `ConcreteState` becomes a stateless singleton (safe to share since it holds no per-context data), and `ContextRegistry` looks up or creates the right `Context` by id instead of the program holding one hardcoded instance.
