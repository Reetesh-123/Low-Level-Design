# Observer Pattern — Class Diagram

`StockObservable` (Subject) is realized by `IphoneObservableImp`. `NotificationAlertObserver` (Observer) is realized by two concrete observers. The subject holds a list of observers and calls `update()` on each one when it changes.

```mermaid
classDiagram
    class Store {
      <<client>>
    }

    class StockObservable {
      <<interface>>
      +add(obj)
      +remove(obj)
      +notifySubscriber()
    }

    class IphoneObservableImp {
      +notifySubscriber()
    }

    class NotificationAlertObserver {
      <<interface>>
      +update()
    }

    class EmailAlertObserverImpl {
      +update()
    }

    class MobileAlertObserverImpl {
      +update()
    }

    Store ..> StockObservable : uses

    StockObservable <|.. IphoneObservableImp : implements
    NotificationAlertObserver <|.. EmailAlertObserverImpl : implements
    NotificationAlertObserver <|.. MobileAlertObserverImpl : implements

    IphoneObservableImp o--> "*" NotificationAlertObserver : notifies
```

## Legend

| Arrow | Meaning |
|---|---|
| `..>` | dependency — uses |
| `<\|..` | realization — class implements interface |
| `o-->` | aggregation — subject holds a list of observers |

## Role mapping

| GoF role | This code |
|---|---|
| Subject | `StockObservable` → `IphoneObservableImp` |
| Observer | `NotificationAlertObserver` → `EmailAlertObserverImpl`, `MobileAlertObserverImpl` |

Rule of thumb: anything typed `StockObservable` is the thing being *watched* — name it `...Observable`, never `...Observer`.
