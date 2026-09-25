# Adapter Design Pattern — Class Diagram

Object adapter: `WeighingScaleAdaptorImpl` implements the Target interface and wraps the Adaptee (pounds) to expose kilograms.

```mermaid
classDiagram
    direction LR

    class Main {
      <<client>>
      +main() void
    }

    class WeighingScaleAdaptor {
      <<interface>>
      +getWeightInKG() int
    }

    class WeighingScaleAdaptorImpl {
      -WeighingScale weighingScale
      +WeighingScaleAdaptorImpl(weighingScale)
      +getWeightInKG() int
    }

    class WeighingScale {
      <<interface>>
      +getWeightInpounds() int
    }

    class WeighingScaleImpl {
      -int weight
      +WeighingScaleImpl(weight)
      +getWeightInpounds() int
    }

    Main ..> WeighingScaleAdaptor : uses (Target)
    WeighingScaleAdaptor <|.. WeighingScaleAdaptorImpl : implements
    WeighingScaleAdaptorImpl o--> WeighingScale : adapts (has-a)
    WeighingScale <|.. WeighingScaleImpl : implements
```

## Legend

| Arrow | Meaning |
|---|---|
| `..>` | dependency — client uses the Target interface only |
| `<\|..` | realization — class implements interface |
| `o-->` | aggregation — adapter holds the adaptee (injected via constructor) |
