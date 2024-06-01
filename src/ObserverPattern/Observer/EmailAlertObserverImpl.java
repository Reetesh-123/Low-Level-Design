package ObserverPattern.Observer;

import ObserverPattern.Observable.StockObservable;

public class EmailAlertObserverImpl implements NotificationAlertObserver {
    
    String emailId;
    StockObservable observable;

    public EmailAlertObserverImpl(String email,StockObservable obj){
        this.emailId=email;
        this.observable=obj;
    }
    
    @Override
    public void update(){
        sendMail(emailId, "Product is in stock hurry up!");
    }
    
    private void sendMail(String emailId, String msg){
        System.out.println("Mail is sent to :" + emailId);
    }
}
