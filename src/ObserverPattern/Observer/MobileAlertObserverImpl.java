package ObserverPattern.Observer;

import ObserverPattern.Observable.StockObservable;

public class MobileAlertObserverImpl implements NotificationAlertObserver{
    String userName;
    StockObservable observable;

    public MobileAlertObserverImpl(String name, StockObservable obj)
    {
        this.observable=obj;
        this.userName=name;
    }

    @Override
    public void update(){
        sentMsgOnMobile(userName, "product is in stock please hurry");
    }

    private void sentMsgOnMobile(String userName,String msg)
    {
        System.out.println("msg sent to " + userName);
    }
}
