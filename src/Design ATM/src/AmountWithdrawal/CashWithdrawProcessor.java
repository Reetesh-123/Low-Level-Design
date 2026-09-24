package AmountWithdrawal;

import ATMRoomComponents.ATM;

public abstract class CashWithdrawProcessor {
    CashWithdrawProcessor nextCashWithdrawProcessor;

     CashWithdrawProcessor (CashWithdrawProcessor nextCashWithdrawProcessor) {
        this.nextCashWithdrawProcessor = nextCashWithdrawProcessor;
    }

    public boolean withdraw(ATM atm, int remainingAmount){
         if(nextCashWithdrawProcessor!=null){
             return nextCashWithdrawProcessor.withdraw(atm, remainingAmount);
         }
         return remainingAmount == 0;
    }
}
