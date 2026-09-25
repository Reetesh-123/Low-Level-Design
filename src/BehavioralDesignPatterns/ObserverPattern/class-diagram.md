# Observer Pattern — Class Diagram

Two sides, don't mix them up: the **`Observable` package holds the Subject role** (the thing being watched, `StockObservable` / `IphoneObservableImp`), and the **`Observer` package holds the Observer role** (the watcher, `NotificationAlertObserver` and its impls). `IphoneObservableImp` is the *Subject*/*Observable* — it keeps a list of `NotificationAlertObserver`s (the *Observers*) and calls `update()` on each when stock comes back. GoF stereotypes are annotated on each class below to make the mapping unambiguous.

```mermaid
classDiagram
    direction LR

    class Store {
      <<client>>
      +main() void
    }

    class StockObservable {
      <<interface>>
      <<Subject>>
      +add(obj) void
      +remove(obj) void
      +notifySubscriber() void
      +setStockCount(newStockAdded) void
      +getStockCount() int
    }

    class IphoneObservableImp {
      <<ConcreteSubject>>
      +int stocks
      +ArrayList~NotificationAlertObserver~ observerList
      +add(obj) void
      +remove(obj) void
      +notifySubscriber() void
      +setStockCount(newStockAdded) void
      +getStockCount() int
    }

    class NotificationAlertObserver {
      <<interface>>
      <<Observer>>
      +update() void
    }

    class EmailAlertObserverImpl {
      <<ConcreteObserver>>
      ~String emailId
      ~StockObservable observable
      +EmailAlertObserverImpl(email, obj)
      +update() void
      -sendMail(emailId, msg) void
    }

    class MobileAlertObserverImpl {
      <<ConcreteObserver>>
      ~String userName
      ~StockObservable observable
      +MobileAlertObserverImpl(name, obj)
      +update() void
      -sentMsgOnMobile(userName, msg) void
    }

    Store ..> StockObservable : uses (var: iphoneStockObservable)
    Store ..> NotificationAlertObserver : uses

    StockObservable <|.. IphoneObservableImp : implements
    NotificationAlertObserver <|.. EmailAlertObserverImpl : implements
    NotificationAlertObserver <|.. MobileAlertObserverImpl : implements

    IphoneObservableImp "1" o--> "*" NotificationAlertObserver : notifies
    EmailAlertObserverImpl --> StockObservable : observes
    MobileAlertObserverImpl --> StockObservable : observes
```

## Legend

| Arrow | Meaning |
|---|---|
| `..>` | dependency — client uses the interfaces |
| `<\|..` | realization — class implements interface |
| `o-->` | aggregation — subject holds its list of observers |
| `-->` | association — observer keeps a reference to the subject |

## Role mapping (avoids the Observer/Observable mix-up)

| GoF role | This code |
|---|---|
| **Subject** (interface) | `StockObservable` (in the `Observable` package) |
| **ConcreteSubject** | `IphoneObservableImp` — the object being watched; holds the observer list |
| **Observer** (interface) | `NotificationAlertObserver` (in the `Observer` package) |
| **ConcreteObserver** | `EmailAlertObserverImpl`, `MobileAlertObserverImpl` — the watchers, each holding a reference back to the subject |

Rule of thumb: if a variable's type is `StockObservable`, it's the thing *being watched* — name it `...Observable`/`...Subject`, never `...Observer` (that was the bug fixed in `Store.java`: `iphoneStockObservable`, previously misnamed `IphoneStockObserver`).
