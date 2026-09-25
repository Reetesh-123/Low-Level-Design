package BehavioralDesignPatterns.ObserverPattern;

import BehavioralDesignPatterns.ObserverPattern.Observable.IphoneObservableImp;
import BehavioralDesignPatterns.ObserverPattern.Observable.StockObservable;
import BehavioralDesignPatterns.ObserverPattern.Observer.EmailAlertObserverImpl;
import BehavioralDesignPatterns.ObserverPattern.Observer.MobileAlertObserverImpl;
import BehavioralDesignPatterns.ObserverPattern.Observer.NotificationAlertObserver;

public class Store {
    public static void main(String[] args) {

       StockObservable iphoneStockObservable = new IphoneObservableImp();

       NotificationAlertObserver emailAlert1 = new EmailAlertObserverImpl("reetesh@gmail.com", iphoneStockObservable);
       NotificationAlertObserver emailAlert2 = new EmailAlertObserverImpl("reetesh2@gmail.com", iphoneStockObservable);
       NotificationAlertObserver mobileAlert = new MobileAlertObserverImpl("Reetesh_Username", iphoneStockObservable);

       iphoneStockObservable.add(emailAlert1);
       iphoneStockObservable.add(emailAlert2);
       iphoneStockObservable.remove(emailAlert2);
       iphoneStockObservable.add(mobileAlert);

       iphoneStockObservable.setStockCount(100);
       iphoneStockObservable.setStockCount(200);
    }
}
