# Flyweight Design Pattern — Class Diagram

`ParticleTypeFactory` caches and reuses `ConcreteParticleType` flyweights (intrinsic state: color, sprite); each `Particle` holds its own extrinsic state (x, y, velocity) plus a reference to a shared `ParticleType`.

```mermaid
classDiagram
    direction TB

    class Main {
      <<client>>
      +main(String[]) void
    }

    class ParticleTypeFactory {
      <<factory>>
      -Map~String, ParticleType~ cache
      +getParticleType(String, String) ParticleType
      +typeCount() int
    }

    class ParticleType {
      <<interface>>
      +draw(float, float, float) void
    }

    class ConcreteParticleType {
      -String color
      -String sprite
      +ConcreteParticleType(String, String)
      +draw(float, float, float) void
    }

    class Particle {
      -ParticleType particleType
      -float x
      -float y
      -float velocity
      +Particle(ParticleType, float, float, float)
      +move(float, float) void
      +draw() void
    }

    Main ..> ParticleTypeFactory : uses
    Main ..> Particle : creates
    ParticleTypeFactory ..> ConcreteParticleType : creates & caches
    ParticleType <|.. ConcreteParticleType : implements
    Particle o--> ParticleType : holds (extrinsic + shared intrinsic)
```

## Legend

| Arrow | Meaning |
|---|---|
| `..>` | dependency — uses / instantiates |
| `<\|..` | realization — class implements interface |
| `o-->` | aggregation — context holds a shared flyweight reference |

Parent-first ordering (`ParticleType <|.. ConcreteParticleType`) keeps the interface ranked above its implementation in the `direction TB` layout.

## Scaling: many instances

A single `ParticleTypeFactory` already handles "many instances" by design — that's the point of the pattern. At LLD-interview scale, the shift is usually to **many concurrent scenes/sessions**, each wanting its own bounded flyweight cache (e.g. per-game-session pooling, or per-tenant caches so one tenant's flyweight variety can't evict another's).

```mermaid
classDiagram
    direction TB

    class ParticleTypeFactoryRegistry {
      <<registry>>
      -Map~String, ParticleTypeFactory~ factories
      +getFactory(String sessionId) ParticleTypeFactory
    }

    class ParticleTypeFactory {
      -Map~String, ParticleType~ cache
      +getParticleType(String, String) ParticleType
    }

    class Particle {
      -ParticleType particleType
    }

    ParticleTypeFactoryRegistry o--> ParticleTypeFactory : creates & caches per session
    Particle ..> ParticleTypeFactoryRegistry : resolves factory per session, not at construction
```
