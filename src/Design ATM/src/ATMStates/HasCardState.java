package ATMStates;

import ATMRoomComponents.ATM;
import ATMRoomComponents.Card;

public class HasCardState extends ATMState{

    @Override
    public void authenticatePin(ATM atm, Card card, int pin) {
        boolean result = card.isCorrectPINEntered(pin);
        if(result){
            atm.setAtmState(new SelectOperationState());
        }
        else {
            System.out.println("Please enter the correct PIN");
            exit(atm);
        }
    }

    @Override
    public void exit(ATM atm) {
        returnCard();
        atm.setAtmState(new IdleState());
        System.out.println("Exit happens");
    }

    @Override
    public void returnCard() {
        System.out.println("Please collect your card");
    }
}
