package ATMStates;

import ATMRoomComponents.ATM;
import ATMRoomComponents.Card;
import enums.TransactionType;

import static enums.TransactionType.CASH_WITHDRAWAL;

public class SelectOperationState extends ATMState {

    @Override
    public void selectOperation(ATM atm, Card card, TransactionType txnType) {
        switch (txnType) {

            case CASH_WITHDRAWAL:
                atm.setAtmState(new CashWithdrawState());
                break;

            case BALANCE_CHECK:
                atm.setAtmState(new CheckBalanceState());
                break;
            default:
                System.out.println("Invalid option");
                exit(atm);
        }
    }
}
