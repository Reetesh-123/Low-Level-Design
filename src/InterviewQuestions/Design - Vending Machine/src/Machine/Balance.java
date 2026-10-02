package Machine;

import java.util.Stack;
import Enum.Coin;

public class Balance {
    int amount;
    Stack<Coin>coins = new Stack<>();

    public int getAmount() {
        return amount;
    }

    public Stack<Coin> getCoins() {
        return coins;
    }

    public void addAmount(Coin coin){
        amount+= coin.getValue();
        coins.push(coin);
    }
    public int processCoin(int price){
        int change = amount - price;
        amount = 0;
        coins.clear();
        return change;
    }

    public Balance(){
        amount = 0;
        coins = new Stack<>();
    }
}
