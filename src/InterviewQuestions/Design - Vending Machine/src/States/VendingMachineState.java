package States;

import Enum.Coin;
import Machine.Inventory;
import Machine.VendingMachine;

import java.util.HashMap;

public abstract class VendingMachineState {
    public VendingMachineState insertCoin(VendingMachine vendingMachine, Coin coin) {
        System.out.println("Cannot insert coin in " + getClass().getSimpleName());
        return this;
    }

    public VendingMachineState selectItem(VendingMachine vendingMachine, String item) {
        System.out.println("Cannot select " + item + " in " + getClass().getSimpleName());
        return this;
    }

    public VendingMachineState dispense(VendingMachine vendingMachine) {
        System.out.println("Cannot dispense in " + getClass().getSimpleName());
        return this;
    }

    public VendingMachineState refill(VendingMachine vendingMachine, String item, int quantity) {
        Inventory inventory = vendingMachine.getInventory();
        HashMap<String,Integer> stock = inventory.getQuantity();
        stock.put(item, stock.getOrDefault(item, 0) + quantity);
        System.out.println("Refilled " + item + ", new quantity: " + stock.get(item));
        return this;
    }

    public VendingMachineState returnCoin(VendingMachine vendingMachine) {
        System.out.println("Cannot return coin in " + getClass().getSimpleName());
        return this;
    }
}
