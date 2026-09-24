package ATMStates;

import ATMRoomComponents.ATM;
import ATMRoomComponents.Card;

public class CheckBalanceState extends ATMState{
    public CheckBalanceState() {
    }

    @Override
    public void displayBalance(ATM atm, Card card) {
        System.out.println("Your Balance is: " + card.getBankBalance());
        exit(atm);
    }
}
