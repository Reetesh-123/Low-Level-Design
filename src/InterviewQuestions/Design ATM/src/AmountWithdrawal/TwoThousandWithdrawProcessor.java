package AmountWithdrawal;

import ATMRoomComponents.ATM;

public class TwoThousandWithdrawProcessor extends CashWithdrawProcessor{
    public TwoThousandWithdrawProcessor(CashWithdrawProcessor nextCashWithdrawProcessor) {
        super(nextCashWithdrawProcessor);
    }

    @Override
    public boolean withdraw(ATM atm, int remainingAmount) {
        int required = remainingAmount/2000;
        int used = Math.min(required, atm.getnoOfTwoThousandNotes());
        int remaining = remainingAmount - used*2000;

        atm.deductTwoThousandNotes(used);

        if(remaining == 0){
            return true;
        }
        return super.withdraw(atm, remaining);
    }
}
