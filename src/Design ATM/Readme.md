# ATM — Low Level Design

A small LLD exercise modeling an ATM's card lifecycle and cash withdrawal, built around two design patterns: **State** for the machine's lifecycle, and **Chain of Responsibility** for breaking a withdrawal into notes.

## Problem being modeled

- Insert card → enter PIN → pick an operation (check balance / withdraw cash) → get the result → card is returned and the machine resets.
- A withdrawal has to succeed or fail as a whole: money only leaves the account if the ATM can physically hand out that amount in its available note denominations (₹2000 / ₹500 / ₹100).

## Pattern 1 — State (drives the ATM's lifecycle)

**Why State fits here:** the ATM's behavior for the *same* action (`cashWithdrawal`, `displayBalance`, ...) is completely different depending on where the customer is in the flow. Instead of a giant `switch` on some `currentStep` enum scattered through every method, each step gets its own class that only implements what's valid *there*.

- **Context**: `ATM` — holds the current state and cash inventory.
- **State**: abstract `ATMState` — declares every possible action (`insertCard`, `authenticatePin`, `selectOperation`, `cashWithdrawal`, `displayBalance`, `returnCard`, `exit`). The base class gives each one a harmless "something went wrong" default, so a concrete state only has to override the handful of actions that are actually valid while it's active — everything else safely no-ops instead of forcing every state to implement every method.
- **Concrete states** and what each one allows:
  - `IdleState` — only `insertCard` is valid.
  - `HasCardState` — only `authenticatePin` is valid; correct PIN moves to `SelectOperationState`, wrong PIN exits back to `IdleState`.
  - `SelectOperationState` — only `selectOperation` is valid; routes to `CheckBalanceState` or `CashWithdrawState` based on the chosen `TransactionType`.
  - `CheckBalanceState` — only `displayBalance` is valid; shows the balance, then exits.
  - `CashWithdrawState` — only `cashWithdrawal` is valid; runs the note-dispensing chain (see below), then exits.
- Every terminal state (`HasCardState`, `SelectOperationState`, `CheckBalanceState`, `CashWithdrawState`) ends the same way: `returnCard()` prints a message, `atm.setAtmState(new IdleState())` resets the machine.

**Who calls what — the Context, not the client.** `ATM` itself exposes the five operations (`atm.insertCard(card)`, `atm.authenticatePin(card, pin)`, ...) and forwards each one to `atmState` internally. The caller (`Main`) never imports or references `ATMState` — it only ever talks to `ATM`, which is the entire point of the pattern: the Context hides which concrete state it's in.

**State transition diagram (informal):**

```
IdleState
  └─ insertCard ─▶ HasCardState
                      ├─ authenticatePin (correct) ─▶ SelectOperationState
                      │                                   ├─ selectOperation(BALANCE_CHECK) ─▶ CheckBalanceState ─▶ exit ─▶ IdleState
                      │                                   └─ selectOperation(CASH_WITHDRAWAL) ─▶ CashWithdrawState ─▶ exit ─▶ IdleState
                      └─ authenticatePin (wrong) ─▶ exit ─▶ IdleState
```

Any failure along the way (wrong PIN, insufficient ATM/account balance, or an amount that can't be represented in available notes) short-circuits straight to `exit()`, which always lands back on `IdleState`.

## Pattern 2 — Chain of Responsibility (splits a withdrawal into notes)

**Why Chain of Responsibility fits here:** dispensing ₹4700 means "use as many ₹2000s as possible, then as many ₹500s as possible with what's left, then ₹100s for the rest." That's a linear sequence of handlers, each responsible for one denomination, each passing on whatever it couldn't cover — exactly the shape of this pattern.

- **Handler**: abstract `CashWithdrawProcessor` — holds a reference to the *next* handler in the chain.
- **Concrete handlers**, chained highest → lowest denomination: `TwoThousandWithdrawProcessor → FiveHundredWithdrawProcessor → HundredWithdrawProcessor`.
- Each handler: dispenses `min(notes needed, notes available)` of its own denomination, computes what's left, and if there's still an amount owing, delegates to the next handler.
- `withdraw()` returns `boolean` — `true` only if the amount was fully represented by available notes by the time the chain runs out of handlers. This is what lets `CashWithdrawState` know whether the withdrawal can actually happen, instead of assuming it always can.
- The chain is currently built by hand inside `CashWithdrawState` (fixed order, fixed 3 denominations) — see "Known, deliberately unfixed smell" below.

## Bugs found and fixed

1. **Shared PIN across all cards.** `Card`'s PIN was a `static` field, so every `Card` instance authenticated against the same number — the class couldn't actually model more than one customer correctly.
   → Fixed: `pin` is now a per-instance field set through the constructor.

2. **Wrong-denomination remainder math.** When `HundredWithdrawProcessor` ran short of ₹100 notes, it recalculated the leftover using the ₹500 handler's multiplier (copy-paste error) — and since it's the last handler in the chain, that wrong number was never corrected, just silently logged.
   → Fixed: every handler now computes the remainder as `amount − used*denomination` (no denomination-specific multiplier to get wrong), and `withdraw()` returns whether the chain actually succeeded.

3. **Money deducted before dispensing was confirmed possible.** `CashWithdrawState` subtracted the amount from the account and the ATM's balance *before* asking the note chain whether it could physically hand out that amount. If the chain then failed (e.g. not enough ₹100s), the account was already debited with nothing dispensed.
   → Fixed: note counts are snapshotted, the chain runs first, and the account/ATM deduction only happens if the chain reports success. On failure, note counts are rolled back to the snapshot — a failed withdrawal is now a true no-op.

4. **State pattern encapsulation was broken.** The client (`Main`) called `atm.getAtmState().insertCard(atm, card)` directly — it had to know `ATMState` existed and had to manually re-pass `atm` into every call, which is exactly what the State pattern is supposed to hide.
   → Fixed: `ATM` now exposes `insertCard`, `authenticatePin`, `selectOperation`, `cashWithdrawal`, `displayBalance` directly, forwarding to the current state internally. The client only ever calls `atm.<action>(...)`.

## Known, deliberately unfixed smell

`CashWithdrawState` hardcodes the chain's construction:
`new TwoThousandWithdrawProcessor(new FiveHundredWithdrawProcessor(new HundredWithdrawProcessor(null)))`
— which couples that state class to all three concrete handler classes and their order. A small `CashWithdrawProcessorFactory` would hide this, but it's only worth the extra indirection if denominations ever become configurable (a differently-stocked ATM). For a fixed 3-denomination machine, this is a minor rigidity, not a correctness issue — left as-is on purpose.

## Package layout

| Package | Contents | Role |
|---|---|---|
| `ATMRoomComponents` | `ATM`, `Card`, `User`, `UserBankAccount` | Domain objects; `ATM` is the State pattern's Context |
| `ATMStates` | `ATMState` + 5 concrete states | State pattern |
| `AmountWithdrawal` | `CashWithdrawProcessor` + 3 concrete handlers | Chain of Responsibility |
| `enums` | `TransactionType` | Operation selector (`CASH_WITHDRAWAL`, `BALANCE_CHECK`) |

`Main.java` is the driver/simulation — it wires up an `ATM` and a `User`/`Card`, then walks through several scenarios (wrong PIN, balance check, successful withdrawal, insufficient balance, un-dispensable amount) purely through the `ATM` facade.

## Class diagram

See [class-diagram.md](./class-diagram.md) for the full Mermaid class diagram (renders inline on GitHub).
