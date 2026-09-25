# Observer Pattern — Class Diagram

`IphoneObservableImp` (subject) keeps a list of `NotificationAlertObserver`s and calls `update()` on each when stock comes back.

```mermaid
classDiagram
    direction LR

    class Store {
      <<client>>
      +main() void
    }

    class StockObservable {
      <<interface>>
      +add(obj) void
      +remove(obj) void
      +notifySubscriber() void
      +setStockCount(newStockAdded) void
      +getStockCount() int
    }

    class IphoneObservableImp {
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
      +update() void
    }

    class EmailAlertObserverImpl {
      ~String emailId
      ~StockObservable observable
      +EmailAlertObserverImpl(email, obj)
      +update() void
      -sendMail(emailId, msg) void
    }

    class MobileAlertObserverImpl {
      ~String userName
      ~StockObservable observable
      +MobileAlertObserverImpl(name, obj)
      +update() void
      -sentMsgOnMobile(userName, msg) void
    }

    Store ..> StockObservable : uses
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
