package Colleague;

import Mediator.Mediator;

public class Bidder implements Colleague {
    private int bid;
    private final String name;
    private final Mediator mediator;

    public Bidder(int bid, String name, Mediator mediator) {
        this.bid = bid;
        this.name = name;
        this.mediator = mediator;
    }

    @Override
    public String getName(){
        return name;
    }

    public void placeBid(int amount){
        bid = amount;
        mediator.broadcastBid(this, amount);
    }

    @Override
    public void receiveBid(String name, int amount){
        IO.println(this.name + " has received the bid of " + amount + " by " + name);
    }
}
