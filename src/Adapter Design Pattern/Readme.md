# Adapter Design Pattern — LLD Notes

Structural pattern: lets a class with an incompatible interface work with code that expects a different one, by wrapping it. See [class-diagram.md](./class-diagram.md).

## Problem

An existing scale (`WeighingScaleImpl`) only reports weight in **pounds**. Client code wants **kilograms**. We can't (or shouldn't) modify the existing class — so we put an adapter in between.

## Roles

| Role | Class | Notes |
|---|---|---|
| **Target** | `WeighingScaleAdaptor` | Interface the client expects — `getWeightInKG()` |
| **Adapter** | `WeighingScaleAdaptorImpl` | Implements Target, **has-a** `WeighingScale`, converts lbs → kg |
| **Adaptee** | `WeighingScale` / `WeighingScaleImpl` | Existing API — `getWeightInpounds()` |
| **Client** | `Main` | Talks only to `WeighingScaleAdaptor` |

## Flow

```
Client --getWeightInKG()--> WeighingScaleAdaptorImpl --getWeightInpounds()--> WeighingScaleImpl
                                   |  (lbs * 0.453592)
Client <------- kg ----------------+
```

## Key points

- **Object adapter (composition)**, not class adapter (inheritance). The adapter holds the adaptee via its **interface** `WeighingScale`, so it works with *any* pound-based scale, not just `WeighingScaleImpl`.
- Adaptee is injected through the constructor → easy to swap/mock.
- Adaptee stays untouched — Open/Closed Principle.
- Class adapter alternative (`extends WeighingScaleImpl implements WeighingScaleAdaptor`) is possible in Java but ties the adapter to one concrete class; composition is preferred.

## Known limitation

`getWeightInKG()` returns `int` and **truncates** via cast: 220 lbs = 99.79 kg → `99`. Use `Math.round(...)` or return `double` if precision matters.

## Real-world examples

- `java.util.Arrays.asList()` — array → `List`
- `java.io.InputStreamReader` — byte stream (`InputStream`) → char stream (`Reader`)
- Wrapping third-party SDKs/payment gateways behind your own interface

## Adapter vs. look-alikes

| Pattern | Intent |
|---|---|
| **Adapter** | Change an interface to one the client expects |
| **Decorator** | Same interface, add behavior |
| **Proxy** | Same interface, control access |
| **Facade** | New simplified interface over a whole subsystem |

## Packages

| Package | Contents |
|---|---|
| `Adaptee` | `WeighingScale`, `WeighingScaleImpl` |
| `Adaptor` | `WeighingScaleAdaptor` (Target), `WeighingScaleAdaptorImpl` (Adapter) |
| (default) | `Main` — client demo |
