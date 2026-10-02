import Enum.Coin;
import Machine.Inventory;
import Machine.VendingMachine;

void main() {
    Inventory inventory = new Inventory();

    inventory.addItem("Coke", 50, 5);
    inventory.addItem("Chips", 100, 3);
    inventory.addItem("Candy", 50, 10);

    VendingMachine vendingMachine = new VendingMachine(inventory);

    printState(vendingMachine, "Initial state");

    System.out.println("\n--- Happy path: buy a Coke ---");
    vendingMachine.insertCoin(Coin.FIFTY);
    vendingMachine.selectItem("Coke");
    vendingMachine.dispense();
    printState(vendingMachine, "After buying Coke");

    System.out.println("\n--- Insufficient balance: try to buy Chips with only 50 ---");
    vendingMachine.insertCoin(Coin.FIFTY);
    vendingMachine.selectItem("Chips");
    printState(vendingMachine, "After insufficient balance attempt");
    vendingMachine.insertCoin(Coin.FIFTY);
    vendingMachine.selectItem("Chips");
    vendingMachine.dispense();
    printState(vendingMachine, "After topping up and buying Chips");

    System.out.println("\n--- Invalid item selection ---");
    vendingMachine.insertCoin(Coin.FIFTY);
    vendingMachine.selectItem("Water");
    printState(vendingMachine, "After selecting unavailable item");
    vendingMachine.returnCoin();
    printState(vendingMachine, "After returning coin");

    System.out.println("\n--- Return coin mid-transaction ---");
    vendingMachine.insertCoin(Coin.HUNDRED);
    vendingMachine.returnCoin();
    printState(vendingMachine, "After return coin");

    System.out.println("\n--- Operations invalid in NoCoinState ---");
    vendingMachine.selectItem("Candy");
    vendingMachine.dispense();
    vendingMachine.returnCoin();
    printState(vendingMachine, "After invalid no-coin-state operations");

    System.out.println("\n--- Refill ---");
    vendingMachine.refill("Candy", 5);
    printState(vendingMachine, "After refill");

    System.out.println("\n--- Sold out flow ---");
    Inventory smallInventory = new Inventory();
    smallInventory.addItem("Water", 50, 1);
    VendingMachine soldOutMachine = new VendingMachine(smallInventory);

    soldOutMachine.insertCoin(Coin.FIFTY);
    soldOutMachine.selectItem("Water");
    soldOutMachine.dispense();
    printState(soldOutMachine, "After depleting the only item");

    soldOutMachine.insertCoin(Coin.FIFTY);

    soldOutMachine.refill("Water", 2);
    printState(soldOutMachine, "After refilling sold-out machine");

    soldOutMachine.insertCoin(Coin.FIFTY);
    soldOutMachine.selectItem("Water");
    soldOutMachine.dispense();
    printState(soldOutMachine, "After buying again post-refill");
}

void printState(VendingMachine vendingMachine, String label) {
    System.out.println(label + " -> state: " + vendingMachine.getVendingMachineState().getClass().getSimpleName());
}
