# Factory Pattern — Class Diagram

`ShapeFactory` hides object creation: the client asks for a shape by name and gets back the `Shape` interface.

```mermaid
classDiagram
    direction TB

    class Main {
      <<client>>
      +main() void
    }

    class ShapeFactory {
      ~getShape(input) Shape
    }

    class Shape {
      <<interface>>
      +draw() void
    }

    class Circle {
      +draw() void
    }
    class Square {
      +draw() void
    }
    class Rectangle {
      +draw() void
    }

    Main ..> ShapeFactory : uses
    Main ..> Shape : uses
    ShapeFactory ..> Circle : creates
    ShapeFactory ..> Square : creates
    ShapeFactory ..> Rectangle : creates

    Shape <|.. Circle : implements
    Shape <|.. Square : implements
    Shape <|.. Rectangle : implements
```

## Legend

| Arrow | Meaning |
|---|---|
| `..>` | dependency — uses / instantiates |
| `<\|..` | realization — class implements interface |
