package ATMRoomComponents;

import ATMStates.ATMState;
import enums.TransactionType;

public class ATM {
    int atmBalance;
    int noOfTwoThousandNotes;
    int noOfFiveHundredNotes;
    int noOfHundredNotes;
    ATMState atmState;

    public ATM(int atmBalance, int noOfTwoThousandNotes, int noOfFiveHundredNotes, int noOfHundredNotes, ATMState atmState) {
        this.atmBalance = atmBalance;
        this.noOfTwoThousandNotes = noOfTwoThousandNotes;
        this.noOfFiveHundredNotes = noOfFiveHundredNotes;
        this.noOfHundredNotes = noOfHundredNotes;
        this.atmState = atmState;
    }

    public int getAtmBalance() {
        return atmBalance;
    }

    public void setAtmBalance(int atmBalance) {
        this.atmBalance = atmBalance;
    }

    public int getnoOfTwoThousandNotes() {
        return noOfTwoThousandNotes;
    }

    public void setnoOfTwoThousandNotes(int noOfTwoThousandNotes) {
        this.noOfTwoThousandNotes = noOfTwoThousandNotes;
    }

    public int getNoOfFiveHundredNotes() {
        return noOfFiveHundredNotes;
    }

    public void setNoOfFiveHundredNotes(int noOfFiveHundredNotes) {
        this.noOfFiveHundredNotes = noOfFiveHundredNotes;
    }

    public int getNoOfHundredNotes() {
        return noOfHundredNotes;
    }

    public void setNoOfHundredNotes(int noOfHundredNotes) {
        this.noOfHundredNotes = noOfHundredNotes;
    }

    public ATMState getAtmState() {
        return atmState;
    }

    public void setAtmState(ATMState atmState) {
        this.atmState = atmState;
    }

    @Override
    public String toString() {
        return "ATM{" +
                "atmBalance=" + atmBalance +
                ", noOfTwoThousandNotes=" + noOfTwoThousandNotes +
                ", noOfFiveHundredNotes=" + noOfFiveHundredNotes +
                ", noOfHundredNotes=" + noOfHundredNotes +
                ", atmState=" + atmState +
                '}';
    }

    public void deductATMBalance(int amount) {
        atmBalance = atmBalance - amount;
    }

    public void deductTwoThousandNotes(int number) {
        noOfTwoThousandNotes = noOfTwoThousandNotes - number;
    }

    public void deductFiveHundredNotes(int number) {
        noOfFiveHundredNotes = noOfFiveHundredNotes - number;
    }

    public void deductOneHundredNotes(int number) {
        noOfHundredNotes = noOfHundredNotes - number;
    }

    public void insertCard(Card card) {
        atmState.insertCard(this, card);
    }

    public void authenticatePin(Card card, int pin) {
        atmState.authenticatePin(this, card, pin);
    }

    public void selectOperation(Card card, TransactionType txnType) {
        atmState.selectOperation(this, card, txnType);
    }

    public void cashWithdrawal(Card card, int withdrawAmount) {
        atmState.cashWithdrawal(this, card, withdrawAmount);
    }

    public void displayBalance(Card card) {
        atmState.displayBalance(this, card);
    }
}
