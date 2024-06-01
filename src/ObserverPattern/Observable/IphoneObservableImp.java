package ObserverPattern.Observable;

import java.util.ArrayList;
import ObserverPattern.Observer.NotificationAlertObserver;

public class IphoneObservableImp implements StockObservable {

    public int stocks=0;
    public ArrayList<NotificationAlertObserver>observerList=new ArrayList<>();

    @Override
   public void add(NotificationAlertObserver obj){
        observerList.add(obj);
    }

    @Override
    public void remove(NotificationAlertObserver obj){
        observerList.remove(obj);
    }

    @Override
    public void notifySubscriber(){
        for(NotificationAlertObserver obj:observerList)
        {
            obj.update();
        }
    }

    @Override
    public void setStockCount(int newStockAdded)
    {
        if(stocks==0){
            notifySubscriber();
            stocks=newStockAdded;
        }
    }
    
    @Override
    public int getStockCount(){
        return stocks;
    }
}
