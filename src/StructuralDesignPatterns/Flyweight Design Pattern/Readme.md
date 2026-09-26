# Flyweight Design Pattern

Structural pattern: minimizes memory use by **sharing** as much data as possible between many similar objects, splitting state into shared **intrinsic** state and per-object **extrinsic** state. See [class-diagram.md](./class-diagram.md).

## What it does

- Splits an object's state into **intrinsic** (shared, immutable, stored in the flyweight — `ConcreteParticleType`'s `color`/`sprite`) and **extrinsic** (unique per object, passed in at call time — `Particle`'s `x`/`y`/`velocity`).
- A factory (`ParticleTypeFactory`) caches flyweights keyed by intrinsic state, so requesting the same combination twice returns the **same instance** instead of allocating a new one.
- Client objects (`Particle`) hold a reference to a shared flyweight and pass their own extrinsic state into its methods (`draw(x, y, velocity)`).

## When to use

- You need to create a **huge number** of similar objects and the per-object memory cost matters (particles, characters in a document, tree/tile instances in a game map).
- Most of the object's state can be made **shared and immutable**; only a small slice is genuinely unique per instance.
- Object identity doesn't matter for these instances — they're interchangeable as long as intrinsic state matches.

## When NOT to use

- Object count is small, or the objects don't share meaningful state — the extra factory/cache indirection isn't worth it.
- Extrinsic/intrinsic state isn't cleanly separable — forcing the split adds complexity without a memory win.

## Roles (GoF)

| Role | Class | Notes |
|---|---|---|
| **Flyweight** | `ParticleType` | Interface declaring the operation that takes extrinsic state |
| **ConcreteFlyweight** | `ConcreteParticleType` | Stores intrinsic state (`color`, `sprite`), shared across many `Particle`s |
| **FlyweightFactory** | `ParticleTypeFactory` | Caches flyweights by key, returns existing instance if present |
| **Client** | `Particle` (context) / `Main` | `Particle` holds extrinsic state + a flyweight reference; `Main` wires it all up |

## Flow

```
Main -> ParticleTypeFactory.getParticleType(color, sprite) -> cache hit? return cached ConcreteParticleType
                                                            -> cache miss? new ConcreteParticleType(...) -> cache & return
Main -> new Particle(particleType, x, y, velocity)
Particle.draw() -> particleType.draw(x, y, velocity)
```

## Key points

- `ParticleTypeFactory.getParticleType` uses `Map.computeIfAbsent` keyed on `"color-sprite"` — one lookup, no separate contains-check race.
- `ConcreteParticleType` fields are `final` — intrinsic state must stay immutable, since one instance is shared by many contexts.
- `Particle` never mutates the flyweight; extrinsic state (`x`, `y`, `velocity`) lives entirely in `Particle` and is passed into `draw(...)` as parameters.
- `typeCount()` exists purely to demonstrate the memory win in `Main` (10 particles, only as many flyweights as distinct color/sprite pairs).

## Flyweight vs. look-alikes

| Pattern | Intent |
|---|---|
| **Flyweight** | Share intrinsic state across many objects to reduce memory |
| **Singleton** | Ensure exactly **one** instance total, not one-per-key |
| **Object Pool** | Reuse **whole, mutable** objects (checked out/in); Flyweight shares **immutable** state, extrinsic state stays external |
| **Factory Method** | Focuses on *how* an object is created, not on *caching/sharing* instances |

## Real-world examples

- `Integer.valueOf(int)` / `Byte`, `Short`, `Long` autoboxing caches (-128..127) — cached wrapper instances
- `String` literal pool (interned strings)
- Character glyph rendering in text editors/document renderers (per-character intrinsic glyph data shared across every occurrence)
- Game engines pooling tile/sprite/particle types across thousands of on-screen entities

## Scaling this pattern

- One in-memory `ParticleTypeFactory` works for a single scene/process. With **many concurrent scenes or sessions**, replace it with a `ParticleTypeFactoryRegistry` keyed by session/scene id, each holding its own bounded cache — so one session's flyweight variety can't unbound another's memory.
- Keep flyweight creation (`computeIfAbsent`) thread-safe if particles are created from multiple threads — `ConcurrentHashMap` or synchronized access around the cache.
- If particles are distributed across processes/machines, the "shared instance" idea doesn't cross a network boundary directly — intrinsic state instead gets replicated (e.g. via a config/reference service or cached lookup table) rather than pointer-shared, but the goal stays the same: don't re-transmit/re-store what's identical across instances.
- If the cache can grow unbounded (huge variety of intrinsic combinations), add eviction (LRU) so `ParticleTypeFactory` itself doesn't become the memory problem it was meant to solve.

## Packages

| Package | Contents |
|---|---|
| `Flyweight` | `ParticleType` (Flyweight interface), `ConcreteParticleType` (ConcreteFlyweight) |
| `FlyweightFactory` | `ParticleTypeFactory` |
| `Client` | `Particle` (context holding extrinsic state) |
| (default) | `Main` — client demo |
