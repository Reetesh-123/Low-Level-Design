//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import Mediator.Auction;
import Colleague.Bidder;

void main() {
    Auction auction = new Auction();
    Bidder bidder1 = new Bidder(0, "Zoro",auction);
    Bidder bidder2 = new Bidder(0, "Nami",auction);
    Bidder bidder3 = new Bidder(0, "Luffy",auction);

    auction.addBidder(bidder1);
    auction.addBidder(bidder2);
    auction.addBidder(bidder3);

    bidder1.placeBid(100);
    IO.println("\n");

    bidder2.placeBid(200);
    IO.println("\n");

    bidder3.placeBid(300);
    IO.println("\n");
}
