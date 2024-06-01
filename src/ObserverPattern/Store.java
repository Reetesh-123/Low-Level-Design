package ObserverPattern;

import ObserverPattern.Observable.IphoneObservableImp;
import ObserverPattern.Observable.StockObservable;
import ObserverPattern.Observer.EmailAlertObserverImpl;
import ObserverPattern.Observer.MobileAlertObserverImpl;
import ObserverPattern.Observer.NotificationAlertObserver;

public class Store {
    public static void main(String[] args) {

       StockObservable IphoneStockObserver = new IphoneObservableImp();

       NotificationAlertObserver emailAlert1 = new EmailAlertObserverImpl("reetesh@gmail.com", IphoneStockObserver);
       NotificationAlertObserver emailAlert2 = new EmailAlertObserverImpl("reetesh2@gmail.com", IphoneStockObserver);
       NotificationAlertObserver mobileAlert = new MobileAlertObserverImpl("Reetesh_Username", IphoneStockObserver);
       
       IphoneStockObserver.add(emailAlert1);
       IphoneStockObserver.add(emailAlert2);
       IphoneStockObserver.remove(emailAlert2);
       IphoneStockObserver.add(mobileAlert);

       IphoneStockObserver.setStockCount(100);
       IphoneStockObserver.setStockCount(200);
    }
}
