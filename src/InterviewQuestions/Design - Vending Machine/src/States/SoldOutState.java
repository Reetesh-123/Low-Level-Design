package States;

import Machine.VendingMachine;

public class SoldOutState extends VendingMachineState{
    @Override
    public VendingMachineState refill(VendingMachine vendingMachine, String item, int quantity) {
        super.refill(vendingMachine, item, quantity);
        return new NoCoinState();
    }
}
