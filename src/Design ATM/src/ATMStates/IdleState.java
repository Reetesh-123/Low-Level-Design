package ATMStates;

import ATMRoomComponents.ATM;
import ATMRoomComponents.Card;

public class IdleState extends ATMState {
    @Override
    public void insertCard(ATM atm, Card card) {
        System.out.println("Card has been inserted\n");
        atm.setAtmState(new HasCardState());
    }
}
