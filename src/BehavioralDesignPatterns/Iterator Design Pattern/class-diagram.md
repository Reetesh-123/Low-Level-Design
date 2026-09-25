# Iterator Design Pattern — Class Diagram

`Aggregate` exposes a factory method that hands out an `Iterator` without revealing whether the collection is a list, array, tree, etc. Each `ConcreteIterator` keeps its own cursor/traversal state, so the same collection can be walked several different ways at once.

```mermaid
classDiagram
    direction TB

    class Iterator {
      <<interface>>
      +hasNext() boolean
      +next() Object
    }

    class ConcreteIteratorA {
      -Collection collection
      -int index
      +hasNext() boolean
      +next() Object
    }

    class ConcreteIteratorB {
      -Collection collection
      -int index
      +hasNext() boolean
      +next() Object
    }

    class Aggregate {
      <<interface>>
      +createIterator(String type) Iterator
    }

    class ConcreteAggregate {
      -List items
      +add(item) void
      +createIterator(String type) Iterator
    }

    class Client {
      <<caller>>
    }

    ConcreteIteratorA ..|> Iterator
    ConcreteIteratorB ..|> Iterator
    ConcreteAggregate ..|> Aggregate
    ConcreteAggregate ..> ConcreteIteratorA : creates
    ConcreteAggregate ..> ConcreteIteratorB : creates
    Client ..> ConcreteAggregate : uses
    Client ..> Iterator : uses (returned type)
```

## Legend

| Arrow | Meaning |
|---|---|
| `..\|>` | realization — implements an interface |
| `..>` | dependency — uses / instantiates |

The client only ever holds an `Iterator` reference and a `ConcreteAggregate` reference — it never sees `ConcreteIteratorA`/`B` by name. That's the whole point: swapping traversal strategy (`"SIMPLE"`, `"SHUFFLE"`, `"FAVORITE"`, ...) doesn't change a single line of client code.
