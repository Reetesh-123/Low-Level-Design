package AmountWithdrawal;

import ATMRoomComponents.ATM;

public class HundredWithdrawProcessor extends CashWithdrawProcessor {
    public HundredWithdrawProcessor(CashWithdrawProcessor nextCashWithdrawProcessor) {
        super(nextCashWithdrawProcessor);
    }

    @Override
    public boolean withdraw(ATM atm, int remainingAmount) {
        int required = remainingAmount/100;
        int used = Math.min(required, atm.getNoOfHundredNotes());
        int remaining = remainingAmount - used*100;

        atm.deductOneHundredNotes(used);

        if(remaining == 0){
            return true;
        }
        return super.withdraw(atm, remaining);
    }

}
