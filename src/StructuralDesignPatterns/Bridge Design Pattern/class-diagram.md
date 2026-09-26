# Bridge Design Pattern — Class Diagram

`LivingThings` (abstraction) holds a `BreatheProcessImplementor` reference instead of implementing breathing itself; `Dog`/`Fish` refine the abstraction while `LandBreatheProcess`/`WaterBreatheProcess` provide the actual breathing implementations — the two hierarchies vary independently.

```mermaid
classDiagram
    direction TB

    class Main {
      <<client>>
      +main() void
    }

    class BreatheProcessImplementor {
      <<interface>>
      +breatheProcess() void
    }

    class LandBreatheProcess {
      +breatheProcess() void
    }

    class WaterBreatheProcess {
      +breatheProcess() void
    }

    class LivingThings {
      <<abstract>>
      #BreatheProcessImplementor breatheProcessImplementor
      +LivingThings(breatheProcessImplementor)
      +breatheProcess() void
    }

    class Dog {
      +Dog(breatheProcessImplementor)
      +breatheProcess() void
    }

    class Fish {
      +Fish(breatheProcessImplementor)
      +breatheProcess() void
    }

    Main ..> LivingThings : uses
    BreatheProcessImplementor <|.. LandBreatheProcess : implements
    BreatheProcessImplementor <|.. WaterBreatheProcess : implements
    LivingThings <|-- Dog : extends
    LivingThings <|-- Fish : extends
    LivingThings o--> BreatheProcessImplementor : bridges to (has-a)
```

## Legend

| Arrow | Meaning |
|---|---|
| `..>` | dependency — client uses the abstraction only |
| `<\|..` | realization — class implements interface |
| `<\|--` | inheritance — class extends abstract class |
| `o-->` | aggregation — abstraction holds the implementor (injected via constructor) |

Parent-first layout note: `BreatheProcessImplementor <|.. LandBreatheProcess` and `LivingThings <|-- Dog` are both written parent-first so the interface/abstract class renders above its implementers/subclasses.

## Scaling: many instances

A single `LivingThings` subclass wired to one implementor works for a single demo. When many concurrent contexts each need their own abstraction/implementor pairing (e.g. per-request or per-tenant), move to a registry keyed by context id instead of constructing pairs ad hoc.

```mermaid
classDiagram
    direction TB

    class Client {
      +main() void
    }

    class LivingThingsRegistry {
      -Map~String, LivingThings~ instancesByContextId
      +getInstance(contextId, type) LivingThings
    }

    class LivingThings {
      <<abstract>>
      #BreatheProcessImplementor breatheProcessImplementor
      +breatheProcess() void
    }

    class Dog
    class Fish
    class BreatheProcessImplementor {
      <<interface>>
    }

    Client ..> LivingThingsRegistry : looks up instance by contextId
    LivingThingsRegistry o--> LivingThings : creates & caches per context
    LivingThings <|-- Dog : extends
    LivingThings <|-- Fish : extends
    LivingThings o--> BreatheProcessImplementor : bridges to (has-a)
```
