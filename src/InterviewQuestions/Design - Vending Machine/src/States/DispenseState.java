package States;

import Machine.Inventory;
import Machine.VendingMachine;

import java.util.HashMap;

public class DispenseState extends VendingMachineState{
    @Override
    public VendingMachineState dispense(VendingMachine vendingMachine) {
        Inventory inventory = vendingMachine.getInventory();
        HashMap<String,Integer> quantity = inventory.getQuantity();
        HashMap<String,Integer> price = inventory.getPrice();
        String selectedItem = vendingMachine.getSelectedItem();

        quantity.put(selectedItem, quantity.get(selectedItem) - 1);
        int change = vendingMachine.getBalance().processCoin(price.get(selectedItem));
        System.out.println("Dispatched " + selectedItem + " (remaining stock: " + quantity.get(selectedItem) + ")");
        System.out.println("Change returned: " + change);

        vendingMachine.setSelectedItem(null);

        if (inventory.isEmpty()) {
            return new SoldOutState();
        }
        return new NoCoinState();
    }
}
