# ATM — LLD Notes

Two patterns: **State** (ATM lifecycle) + **Chain of Responsibility** (cash dispensing). See [class-diagram.md](./class-diagram.md).

## State — lifecycle

- **Context**: `ATM` — exposes `insertCard/authenticatePin/selectOperation/cashWithdrawal/displayBalance`, forwards each to the current `ATMState`. Client only ever talks to `ATM`, never `ATMState` directly.
- **Abstract `ATMState`**: default "OOPS" body for invalid ops in a state; `exit()`/`returnCard()` are implemented once here (shared), so concrete states only override what's actually valid for them.

```
Idle --insertCard--> HasCard --authenticatePin(ok)--> SelectOperation --BALANCE_CHECK--> CheckBalance --> exit --> Idle
                        |                                  --CASH_WITHDRAWAL--> CashWithdraw --> exit --> Idle
                        --authenticatePin(wrong)--> exit --> Idle
```
Any failure (wrong PIN / insufficient balance / can't dispense) → `exit()` → `Idle`.

## Chain of Responsibility — note dispensing

- Abstract `CashWithdrawProcessor` holds `next` handler as an **instance field** + base delegation logic.
- Chain: `2000 → 500 → 100`. Each handler uses `min(needed, available)`, passes the remainder down.
- `withdraw()` returns `boolean` (fully dispensed or not). `CashWithdrawState` snapshots note counts, runs the chain, commits the balance deduction only on `true`, else rolls back — a failed withdrawal is a true no-op.

## Bugs fixed

1. `Card.pin` was `static` (one PIN for every card) → instance field via constructor.
2. Chain's remainder math used the wrong denomination's multiplier and swallowed failures → `boolean` return + `amount − used*denomination`.
3. Balances were deducted *before* confirming the chain could dispense → snapshot + rollback on failure.
4. Client bypassed the Context (`atm.getAtmState().x(atm,...)`) → moved onto `ATM` itself (see above).
5. `exit()`/`returnCard()` duplicated in 4 states → pulled up into `ATMState`'s base implementation.

## Left as-is (minor smell)

Chain is hand-built inside `CashWithdrawState` (`new TwoThousandWithdrawProcessor(...)`) — couples it to all 3 concrete handlers. Only worth a factory if denominations become configurable.

## Abstract class vs. interface

- `ATMState` — **interface with `default` methods would also work.** No instance fields, so nothing blocks it; abstract class was picked to leave room for future shared state (e.g. retry counters).
- `CashWithdrawProcessor` — **must stay an abstract class.** It holds `next` as an instance field; interfaces can't have instance fields, and there's no clean way to keep the shared-chain behavior without one.

## Packages

| Package | Contents |
|---|---|
| `ATMRoomComponents` | `ATM` (Context), `Card`, `User`, `UserBankAccount` |
| `ATMStates` | `ATMState` + 5 concrete states |
| `AmountWithdrawal` | `CashWithdrawProcessor` + 3 handlers |
| `enums` | `TransactionType` |
