# Proxy Design Pattern

Structural pattern: provides a stand-in for another object that implements the same interface, so the client can't tell it apart from the real thing, while the stand-in controls access to (and/or the creation of) the real object. See [class-diagram.md](./class-diagram.md).

## What it does

- `VideoServiceProxy` implements `VideoService` — the exact same interface `RealVideoService` implements — so `Main` can hold either one behind a `VideoService` reference without knowing which it has.
- `RealVideoService` contains **only** the actual streaming logic (`System.out.println("Streaming video: " + videoName)`). It has no idea `userType` is even being validated.
- `VideoServiceProxy.playVideo` enforces the access rule **before** anything reaches the real object: only `"premium"` or `"free"` are allowed through; anything else (e.g. `"guest"`) prints `"Access denied: Invalid user type."` and returns — `RealVideoService` is never touched.
- `VideoServiceProxy` also lazily creates `RealVideoService` — the real object is only instantiated the first time a call actually passes the access check, not up front.
- `Main` depends only on `VideoService` and `VideoServiceProxy`. It never references `RealVideoService` directly — that's the point: the real subject stays hidden behind the proxy.

## When to use

- You need to control access to an object based on caller identity/permissions (protection proxy).
- The real object is expensive to create and should be deferred until it's actually needed (virtual proxy).
- You want to add logging, caching, or remote-call marshalling around an object without changing its code or the client's.

## When NOT to use

- There's no real reason to intercept the call (no access control, no lazy init, no cross-cutting concern to add) — a direct reference is simpler.
- The "control" logic naturally belongs to the real object itself and won't ever need to vary independently of it.

## Roles (GoF)

| Role | Class | Notes |
|---|---|---|
| **Subject** | `VideoService` | Interface — `playVideo(userType, videoName)` |
| **Real Subject** | `RealVideoService` | Does the actual work only — no access-control logic |
| **Proxy** | `VideoServiceProxy` | Implements `VideoService`, checks `userType`, lazily creates and delegates to `RealVideoService` |
| **Client** | `Main` | Talks only to `VideoService`, holds a `VideoServiceProxy` |

## Flow

```
Client --playVideo("premium", name)--> VideoServiceProxy
                                              | userType allowed? (premium/free)
                                              | yes -> lazily create RealVideoService (first call only)
                                              v
                                        RealVideoService.playVideo() --> "Streaming video: ..."

Client --playVideo("guest", name)--> VideoServiceProxy
                                              | userType allowed? no
                                              v
                                        "Access denied: Invalid user type." (RealVideoService never called)
```

## Key points

- **Access control lives in the proxy, not the real subject.** Before the refactor, `RealVideoService` had the `switch` on `userType` — that meant the real object had to know about permissions, and any code that got a direct `RealVideoService` reference would bypass the check entirely. Moving it to `VideoServiceProxy` means the check is enforced no matter how the client reaches the service, as long as it goes through the proxy.
- **Lazy initialization**: `realVideoService` starts `null` and is only created inside `playVideo`, after the access check passes. A denied call never constructs a `RealVideoService` at all.
- **Same interface, interchangeable**: because `VideoServiceProxy implements VideoService`, `Main` can declare `VideoService videoService = new VideoServiceProxy();` and never mention `RealVideoService` — swapping in a plain `RealVideoService` (no access control) is a one-line change in `Main`, not a redesign.

## Proxy vs. look-alikes

| Pattern | Intent |
|---|---|
| **Proxy** | Same interface, control access/creation to an existing object |
| **Adapter** | Change an interface to one the client expects |
| **Decorator** | Same interface, add behavior, usually stackable and applied at runtime by choice |
| **Facade** | New simplified interface over a whole subsystem, not a stand-in for one object |

## Real-world examples

- `java.rmi` remote proxies — a local stub stands in for an object living on another JVM
- Hibernate/JPA lazy-loaded entity proxies — the real entity is only fetched from the DB on first access
- Spring AOP proxies — wrap a bean to add transactions/security checks without touching the bean's code
- `java.lang.reflect.Proxy` — dynamic proxies used for logging, access control, or mocking around any interface

## Scaling this pattern

- One `VideoServiceProxy` wrapping one lazily-created `RealVideoService` works for a single demo run. At interview scale, replace ad hoc construction with a `VideoServiceProxyRegistry` keyed by user/session id — create-and-cache a proxy (and its lazily-created real subject) per key instead of one shared instance.
- If `RealVideoService` starts holding per-context state (e.g. a streaming session or connection), the proxy should resolve it per call via the registry rather than binding to one instance at construction, so concurrent users don't share mutable state.
- The lazy-init check (`if (realVideoService == null)`) is not thread-safe as written — under concurrent access from multiple threads it could construct more than one `RealVideoService`. Guard it (double-checked locking, `synchronized`, or a thread-safe holder) once the proxy is shared across threads.
- When the real subject moves behind a network boundary (e.g. streaming is actually served by a remote service), the proxy's delegation call becomes a remote call (HTTP/gRPC) instead of an in-process method call — the proxy keeps the same `VideoService` interface for the client but now also owns timeouts/retries/error translation for that call.

## Packages

| Package | Contents |
|---|---|
| `Subject` | `VideoService` (Subject interface) |
| `RealSubject` | `RealVideoService` (Real Subject) |
| `Proxy` | `VideoServiceProxy` (Proxy) |
| (default) | `Main` — client demo |
