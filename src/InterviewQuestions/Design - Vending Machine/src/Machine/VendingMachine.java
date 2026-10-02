package Machine;
import States.NoCoinState;
import States.VendingMachineState;
import Enum.Coin;

public class VendingMachine {
    VendingMachineState vendingMachineState;
    Inventory inventory;
    Balance balance;
    String selectedItem;

    public VendingMachine(Inventory inventory){
        this.inventory = inventory;
        this.balance = new Balance();
        this.vendingMachineState = new NoCoinState();
    }

    public VendingMachineState getVendingMachineState() {
        return vendingMachineState;
    }

    public void setVendingMachineState(VendingMachineState vendingMachineState) {
        this.vendingMachineState = vendingMachineState;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public void insertCoin(Coin coin){
        vendingMachineState = vendingMachineState.insertCoin(this,coin);
    }

    public void selectItem(String item) {
        vendingMachineState = vendingMachineState.selectItem(this,item);    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void dispense() {
        vendingMachineState = vendingMachineState.dispense(this);
    }

    public void refill(String item, int quantity) {
       vendingMachineState = vendingMachineState.refill(this,item,quantity);
    }

    public void returnCoin() {
        vendingMachineState = vendingMachineState.returnCoin(this);
    }
}
