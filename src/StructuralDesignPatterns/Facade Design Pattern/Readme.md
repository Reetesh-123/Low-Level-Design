# Facade Design Pattern

Structural pattern: provides a single simplified interface over a set of subsystem classes, hiding their internal calls/ordering. See [class-diagram.md](./class-diagram.md).

## What it does

- Wraps a group of subsystem classes (`ImageViewer`, `MusicPlayer`, `VideoPlayer`) behind one class (`MediaFacade`).
- Each facade method (`playMusic`, `viewImage`, `playVideo`) internally calls the right subsystem methods in the right order, so the client doesn't need to know that sequence.
- Client depends only on the facade, never on the subsystem classes directly.

## When to use

- A subsystem has many classes and/or a specific call order that's easy to get wrong.
- You want to decouple client code from a complex/changing subsystem.
- You want a simple entry point for common use cases, without hiding the subsystem's full API for callers who need it.

## When NOT to use

- The subsystem is already simple (one or two classes, no ordering constraints) — a facade just adds an extra layer.
- Callers need fine-grained control over subsystem internals — a facade that hides too much forces workarounds.

## Roles (GoF)

| Role | Class | Notes |
|---|---|---|
| **Facade** | `MediaFacade` | Single entry point — `playMusic()`, `viewImage()`, `playVideo()` |
| **Subsystem classes** | `ImageViewer`, `MusicPlayer`, `VideoPlayer` | Independent classes with their own multi-step APIs |
| **Client** | `Main` | Talks only to `MediaFacade`, never to the subsystem classes |

## Flow

```
Client --playMusic()--> MediaFacade --InitializeAudioDriver()/decodeAudio()/startPlayback()--> MusicPlayer
Client --viewImage()--> MediaFacade --loadImageFile()/applyScaling()/displayImage()--> ImageViewer
Client --playVideo()--> MediaFacade --setupRenderingEngine()/loadVideoFile()/playVideo()--> VideoPlayer
```

## Key points

- Subsystem classes are injected into `MediaFacade`'s constructor — easy to swap/mock for tests.
- Facade doesn't add new behavior, only sequencing/delegation — the subsystem classes still do the real work.
- Subsystem classes remain usable directly by callers who need lower-level control; the facade is an added convenience, not the only way in.

## Facade vs. look-alikes

| Pattern | Intent |
|---|---|
| **Facade** | New simplified interface over a whole subsystem |
| **Adapter** | Change an interface to one the client expects |
| **Mediator** | Centralizes communication *between* peer objects, which don't call each other directly |
| **Proxy** | Same interface as the real object, controls access to it |

## Real-world examples

- `javax.faces.context.FacesContext` — facade over many JSF subsystems
- `java.net.HttpURLConnection` — hides socket/protocol handling behind a simple request/response API
- ORM libraries (e.g. Hibernate's `Session`) — facade over SQL generation, connection handling, caching
- SDK "client" classes (e.g. an AWS `S3Client`) — facade over auth, retries, request signing

## Scaling this pattern

- One `MediaFacade` field works for a single-session demo. At interview scale, replace the single instance with a `MediaFacadeRegistry` keyed by session/context id — create-and-cache a facade (with its own subsystem instances) per key, and look it up per request instead of constructing one at startup.
- Subsystem classes that hold per-session state must not be shared across sessions — each registry entry gets its own subsystem instances, not references to a single shared set.
- If a facade method mutates shared state (e.g. a shared cache or connection pool inside a subsystem), guard that mutation point with synchronization or a thread-safe collection — the facade itself doesn't need to be thread-safe if its subsystems are.
- When subsystems move to separate services (e.g. video transcoding as its own microservice), the facade's delegation calls become network calls (HTTP/gRPC/message queue) instead of in-process method calls — the facade keeps the same simplified interface for the client, but now also owns timeouts/retries/error translation for those calls.

## Packages

| Package | Contents |
|---|---|
| `Facade` | `MediaFacade` |
| `Multimedia` | `ImageViewer`, `MusicPlayer`, `VideoPlayer` |
| (default) | `Main` — client demo |
