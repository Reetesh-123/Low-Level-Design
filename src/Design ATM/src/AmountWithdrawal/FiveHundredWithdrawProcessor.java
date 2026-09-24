package AmountWithdrawal;

import ATMRoomComponents.ATM;

public class FiveHundredWithdrawProcessor extends CashWithdrawProcessor{
    public FiveHundredWithdrawProcessor(CashWithdrawProcessor nextCashWithdrawProcessor) {
        super(nextCashWithdrawProcessor);
    }

    @Override
    public boolean withdraw(ATM atm, int remainingAmount) {
        int required = remainingAmount/500;
        int used = Math.min(required, atm.getNoOfFiveHundredNotes());
        int remaining = remainingAmount - used*500;

        atm.deductFiveHundredNotes(used);

        if(remaining == 0){
            return true;
        }
        return super.withdraw(atm, remaining);
    }
}
