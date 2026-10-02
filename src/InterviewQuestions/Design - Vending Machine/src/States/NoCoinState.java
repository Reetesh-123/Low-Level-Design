package States;

import Enum.Coin;
import Machine.Balance;
import Machine.VendingMachine;


public class NoCoinState extends VendingMachineState{

    public VendingMachineState insertCoin(VendingMachine vendingMachine, Coin coin){
        Balance balance = vendingMachine.getBalance();
        balance.addAmount(coin);
        System.out.println("Inserted " + coin + " (" + coin.getValue() + "), balance: " + balance.getAmount());
        return new HasCoinState();
    }

}
