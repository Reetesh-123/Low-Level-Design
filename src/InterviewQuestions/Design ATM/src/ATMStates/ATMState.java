package ATMStates;

import ATMRoomComponents.ATM;
import ATMRoomComponents.Card;
import enums.TransactionType;

public abstract class ATMState {
    public void insertCard(ATM atm, Card card) {
        System.out.println("OOPS!! Something went wrong");
    }

    public void authenticatePin(ATM atm, Card card, int pin) {
        System.out.println("OOPS!! Something went wrong");
    }

    public void selectOperation(ATM atm, Card card, TransactionType txnType) {
        System.out.println("OOPS!! Something went wrong");
    }

    public void cashWithdrawal(ATM atm, Card card, int withdrawAmount) {
        System.out.println("OOPS!! Something went wrong");
    }

    public void displayBalance(ATM atm, Card card) {
        System.out.println("OOPS!! Something went wrong");
    }

    public void returnCard() {
        System.out.println("Please collect your card");
    }

    public void exit(ATM atm) {
        returnCard();
        atm.setAtmState(new IdleState());
        System.out.println("Exit happens");
    }

}
