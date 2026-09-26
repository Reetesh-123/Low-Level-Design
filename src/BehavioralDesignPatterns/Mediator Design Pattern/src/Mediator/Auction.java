package Mediator;

import java.util.ArrayList;
import java.util.List;

import Colleague.Colleague;

public class Auction implements Mediator {
    private int bid;
    private final List<Colleague> persons;

    public Auction(){
        persons = new ArrayList<>();
        bid = 0;
    }

    @Override
    public void addBidder(Colleague colleague){
        persons.add(colleague);
    }

    @Override
    public void removeBidder(Colleague colleague){
        persons.remove(colleague);
    }

    @Override
    public void broadcastBid(Colleague sender, int amount){
        if(amount <= this.bid){
            return;
        }
        this.bid = amount;
        for(Colleague colleague : persons){
            if(colleague != sender){
                colleague.receiveBid(sender.getName(), amount);
            }
        }
    }

}
