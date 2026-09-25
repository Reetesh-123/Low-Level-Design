# File System — LLD Notes

Pattern: **Composite** — treat a single object (`File`) and a group of objects (`Directory`) the same way through one interface. See [class-diagram.md](./class-diagram.md).

## When to use

Data is a **tree** (part–whole hierarchy) and the client shouldn't care whether it holds a leaf or a container. Examples: file systems, UI component trees, org charts, menus, arithmetic expression trees.

## Roles

| Role | Class | Job |
|---|---|---|
| **Component** | `FileSystem` (interface) | Common operation: `ls()` |
| **Leaf** | `File` | Does the work itself — prints its name. No children. |
| **Composite** | `Directory` | Holds `List<FileSystem>`; `ls()` just delegates to each child. |
| **Client** | `Main` | Builds the tree, calls `ls()` on any node. |

## Key idea

```
root (Directory)
 ├── documents (Directory)
 │    ├── resume.pdf (File)
 │    └── cover_letter.docx (File)
 ├── media (Directory)
 │    ├── holiday.jpg (File)
 │    └── song.mp3 (File)
 └── notes.txt (File)
```

- `Directory` holds `FileSystem`, **not** `File` → a directory can contain files *and* other directories. This one line makes the recursion possible.
- `root.ls()` → each child's `ls()` → recursion ends at `File` (the base case).
- Client code is identical for a file or a directory: `node.ls()`. No `instanceof`, no if/else.

## Benefits

- **Uniformity** — one interface for leaf and composite.
- **Open/Closed** — add a new node type (e.g. `Shortcut`) by implementing `FileSystem`; nothing else changes.
- **Recursive ops for free** — `size()`, `search()`, `delete()` all follow the same pattern as `ls()`.

## Gaps in the current version

1. `Directory` has **no name** → `ls()` prints a flat list; hierarchy isn't visible. Fix: add `name` and print with indentation per depth.
2. **No `add()` / `remove()`** → children only via constructor. Fix: `add(FileSystem)` on `Directory`.
3. Fields are package-private → make them `private final`.

## Interview design choice: where does `add()` go?

- **On `Directory` only** (safe design) — `File` can't have children at compile time; client must know it has a `Directory` to add.
- **On `FileSystem`** (transparent design) — fully uniform, but `File.add()` must throw `UnsupportedOperationException`.
- Prefer the **safe** design; mention the trade-off.
