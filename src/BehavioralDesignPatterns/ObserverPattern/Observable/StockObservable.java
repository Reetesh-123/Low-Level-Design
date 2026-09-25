package BehavioralDesignPatterns.ObserverPattern.Observable;

import BehavioralDesignPatterns.ObserverPattern.Observer.NotificationAlertObserver;

public interface StockObservable {
   public void add(NotificationAlertObserver obj);
   public void remove(NotificationAlertObserver obj);
   public void notifySubscriber();
   public void setStockCount(int newStockAdded);
   public int getStockCount();
}
