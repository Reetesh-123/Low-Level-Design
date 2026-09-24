package ATMStates;

import ATMRoomComponents.ATM;
import ATMRoomComponents.Card;
import AmountWithdrawal.CashWithdrawProcessor;
import AmountWithdrawal.FiveHundredWithdrawProcessor;
import AmountWithdrawal.HundredWithdrawProcessor;
import AmountWithdrawal.TwoThousandWithdrawProcessor;

public class CashWithdrawState extends ATMState{
    @Override
    public void cashWithdrawal(ATM atm, Card card, int withdrawAmount) {

        if(withdrawAmount> atm.getAtmBalance()) {
            System.out.println("ATM doesn't have enough balance");
            exit(atm);
            return;
        }
        else if(card.getBankBalance() < withdrawAmount){
            System.out.println("Account doesn't have enough balance");
            exit(atm);
            return;
        }
        int savedTwoThousandNotes = atm.getnoOfTwoThousandNotes();
        int savedFiveHundredNotes = atm.getNoOfFiveHundredNotes();
        int savedHundredNotes = atm.getNoOfHundredNotes();

        CashWithdrawProcessor withdrawProcessor = new TwoThousandWithdrawProcessor(new FiveHundredWithdrawProcessor(new HundredWithdrawProcessor(null)));
        boolean dispensed = withdrawProcessor.withdraw(atm, withdrawAmount);

        if (!dispensed) {
            atm.setnoOfTwoThousandNotes(savedTwoThousandNotes);
            atm.setNoOfFiveHundredNotes(savedFiveHundredNotes);
            atm.setNoOfHundredNotes(savedHundredNotes);
            System.out.println("ATM cannot dispense this amount with available denominations");
            exit(atm);
            return;
        }

        card.deductBankBalance(withdrawAmount);
        atm.deductATMBalance(withdrawAmount);
        exit(atm);
    }

    @Override
    public void exit(ATM atmObject) {
        returnCard();
        atmObject.setAtmState(new IdleState());
        System.out.println("Exit happens");
    }

    @Override
    public void returnCard() {
        System.out.println("Please collect your card");
    }
}

