# Singleton Design Pattern

Creational pattern: restricts a class to **exactly one instance** for its entire lifetime, and gives global access to that one instance. See [class-diagram.md](./class-diagram.md).

## What it does

- Guarantees only **one object** of the class ever exists in the JVM (per classloader).
- Provides a single, well-known **access point** (`getInstance()`) instead of letting callers `new` the class freely.
- Hides construction: constructor is **private**, so instantiation is only possible from inside the class itself.
- Typically **lazily created** (built on first use) rather than eagerly, though both approaches exist.

## When to use

- Exactly one instance must coordinate actions across the whole app — e.g. a shared config, connection pool, cache, logger, or thread pool.
- Creating the object is expensive (I/O, network, heavy setup) and you want to create it once and reuse it.
- You need a single source of truth for shared state accessed from many unrelated parts of the code.

## When NOT to use

- It's really just global mutable state in disguise — makes unit testing hard (can't swap in a mock/fake easily) and hides dependencies.
- You need multiple instances later (e.g. multi-tenant configs) — Singleton bakes in "exactly one" as an architectural assumption that's hard to walk back.
- Dependency injection (framework-managed single instance, `@Singleton`/`@Component` scope) achieves the same goal **without** the private-constructor/global-access downsides — prefer it when a DI framework is already in play.

## How to use (small example)

```java
public class ConfigManager {
    // eager: created once at class-load, thread-safe for free
    private static final ConfigManager INSTANCE = new ConfigManager();

    private ConfigManager() { /* load config */ }

    public static ConfigManager getInstance() {
        return INSTANCE;
    }
}

// Lazy + thread-safe alternative (double-checked locking)
public class Logger {
    private static volatile Logger instance;

    private Logger() { }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }
}

// Client
ConfigManager cfg = ConfigManager.getInstance();
```

Key idioms:
- **Private constructor** blocks `new Singleton()` from outside.
- **`volatile` + double-checked locking** avoids a lock on every call while staying thread-safe for lazy init.
- **Eager init** (`static final INSTANCE = new X()`) sidesteps synchronization entirely — relies on the JVM's class-loading guarantees. Simplest choice when the object is cheap to build or always needed.
- **Enum singleton** (`enum Singleton { INSTANCE; }`) is the JVM-safest form — free serialization/reflection protection.

## Diagram

```
Client -> Singleton.getInstance() -> (instance == null?) -> new Singleton() -> return instance
Client -> Singleton.getInstance() -> (instance != null)   -> return same instance
```

## Roles (GoF)

| Role | Responsibility |
|---|---|
| **Singleton** | Declares the static `getInstance()` accessor, guards the private constructor, and holds the sole instance |
| **Client** | Calls `getInstance()` instead of `new` |

## Singleton vs. look-alikes

| Pattern | Intent |
|---|---|
| **Singleton** | Exactly one instance, global access point |
| **Static class / utility class** | No instance at all — just grouped stateless functions |
| **Factory Method** | Creates instances (possibly many, possibly different subtypes) on request |
| **Multiton** | One instance **per key**, instead of one instance total |
| **DI-managed singleton scope** | Same "one instance" goal, but container owns lifecycle — no private constructor, easily mocked in tests |

## Real-world examples

- `java.lang.Runtime.getRuntime()`
- `java.awt.Desktop.getDesktop()`
- Spring's default bean scope (`singleton`) — one instance per Spring container
- Logging frameworks (e.g. Log4j's `LogManager` returns shared `Logger` instances per name)
