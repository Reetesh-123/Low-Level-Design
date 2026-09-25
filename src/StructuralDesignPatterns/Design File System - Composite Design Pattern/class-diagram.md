# File System — Class Diagram

Composite pattern: `File` is the leaf, `Directory` is the composite, both are `FileSystem`.

```mermaid
classDiagram
    direction TB

    class FileSystem {
      <<interface>>
      +ls() void
    }

    class File {
      String filename
      +File(filename)
      +ls() void
    }

    class Directory {
      List~FileSystem~ fileSystemList
      +Directory(fileSystemList)
      +ls() void
    }

    class Main {
      +main(args) void
    }

    FileSystem <|.. File : implements (leaf)
    FileSystem <|.. Directory : implements (composite)
    Directory "1" o-- "0..*" FileSystem : children
    Main ..> FileSystem : uses
```

The key relationship is `Directory o-- FileSystem`: a directory holds the *interface*, so it can contain files or other directories, which gives the recursive tree.
