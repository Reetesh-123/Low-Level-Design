package Mediator;

import Colleague.Colleague;

public interface Mediator {
    void addBidder(Colleague colleague);
    void removeBidder(Colleague colleague);
    void broadcastBid(Colleague sender, int amount);
}
