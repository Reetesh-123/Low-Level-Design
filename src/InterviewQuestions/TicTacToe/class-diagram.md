# Tic Tac Toe — Class Diagram

`Game` runs the turn loop over a deque of `Player`s. `Board` stores the grid and keeps per-row/column/diagonal counts keyed by `(index, PieceType)`, so `checkGame()` is O(1).

```mermaid
classDiagram
    direction TB

    class Main {
      <<client>>
      +main() void
    }

    class Game {
      ~Deque~Player~ list
      ~Board board
      +Game()
      +intialise() void
      +startGame() void
    }

    class Board {
      ~int size
      ~PlayingPiece[][] board
      ~HashMap rowSum
      ~HashMap colSum
      ~HashMap leftDiagonal
      ~HashMap rightDiagonal
      +int remainingPieces
      +Board(size)
      +addPiece(row, col, piece) boolean
      +printBoard() void
      +checkGame(row, col, piece) boolean
    }

    class Player {
      +String name
      +PlayingPiece piece
      +Player(name, piece)
    }

    class PlayingPiece {
      ~PieceType type
      +PlayingPiece(type)
    }

    class PieceX {
      +PieceX()
    }

    class PieceO {
      +PieceO()
    }

    class PieceType {
      <<enumeration>>
      X
      O
    }

    Main ..> Game : starts
    Game "1" *-- "1" Board : owns
    Game "1" *-- "2" Player : turn queue
    Player "1" o-- "1" PlayingPiece : plays with
    Board "1" o-- "0..*" PlayingPiece : grid cells

    PlayingPiece <|-- PieceX
    PlayingPiece <|-- PieceO
    PlayingPiece --> PieceType : type
```

## Legend

| Arrow | Meaning |
|---|---|
| `..>` | dependency — uses |
| `*--` | composition — created and owned by `Game.intialise()` |
| `o--` | aggregation — holds references to pieces |
| `<\|--` | inheritance — extends class |
| `-->` | association — field reference |
