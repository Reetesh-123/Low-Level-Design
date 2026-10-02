package States;

import Enum.Coin;
import Machine.Balance;
import Machine.Inventory;
import Machine.VendingMachine;

import java.util.HashMap;

public class HasCoinState extends VendingMachineState{
    @Override
    public VendingMachineState insertCoin(VendingMachine vendingMachine,Coin coin) {
        Balance balance = vendingMachine.getBalance();
        balance.addAmount(coin);
        System.out.println("Inserted " + coin + " (" + coin.getValue() + "), balance: " + balance.getAmount());
        return this;
    }

    @Override
    public VendingMachineState selectItem(VendingMachine vendingMachine, String item) {
        Inventory inventory = vendingMachine.getInventory();
        int amount = vendingMachine.getBalance().getAmount();
        HashMap<String,Integer>quantity = inventory.getQuantity();
        HashMap<String,Integer>price = inventory.getPrice();

        if(!quantity.containsKey(item) || quantity.get(item) <= 0){
            System.out.println("Item unavailable: " + item);
            return this;
        }
        if(price.get(item)> amount){
            System.out.println("Insufficient balance for " + item + " (price: " + price.get(item) + ", inserted: " + amount + ")");
            return this;
        }

        vendingMachine.setSelectedItem(item);
        return new DispenseState();
    }

    @Override
    public VendingMachineState dispense(VendingMachine vendingMachine) {
        return null;
    }

    @Override
    public VendingMachineState returnCoin(VendingMachine vendingMachine) {
        int refund = vendingMachine.getBalance().processCoin(0);
        System.out.println("Returned coins, refund: " + refund + " (balance cleared)");
        return new NoCoinState();
    }
}
