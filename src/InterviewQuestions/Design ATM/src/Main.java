import ATMRoomComponents.ATM;
import ATMRoomComponents.Card;
import ATMRoomComponents.User;
import ATMRoomComponents.UserBankAccount;
import ATMStates.IdleState;
import enums.TransactionType;

public class Main {

    public static void main(String[] args) {

        // Set up a fresh ATM with cash reserves.
        ATM atm = new ATM(50000, 10, 20, 50, new IdleState());

        // Set up a user with a bank account and card.
        UserBankAccount bankAccount = new UserBankAccount(10000);
        int pin = 1122;
        Card card = new Card(123456, 999, 202812, 987654, pin, bankAccount);
        User user = new User(bankAccount, card);

        System.out.println("Initial ATM state: " + atm);
        System.out.println("Initial account balance: " + bankAccount.getBalance());

        System.out.println("\n--- Scenario 1: Wrong PIN ---");
        atm.insertCard(user.getCard());
        atm.authenticatePin(user.getCard(), 999999);

        System.out.println("\n--- Scenario 2: Check balance ---");
        atm.insertCard(user.getCard());
        atm.authenticatePin(user.getCard(), pin);
        atm.selectOperation(user.getCard(), TransactionType.BALANCE_CHECK);
        atm.displayBalance(user.getCard());

        System.out.println("\n--- Scenario 3: Cash withdrawal ---");
        atm.insertCard(user.getCard());
        atm.authenticatePin(user.getCard(), pin);
        atm.selectOperation(user.getCard(), TransactionType.CASH_WITHDRAWAL);
        atm.cashWithdrawal(user.getCard(), 4700);

        System.out.println("\nAccount balance after withdrawal: " + bankAccount.getBalance());
        System.out.println("ATM state after withdrawal: " + atm);

        System.out.println("\n--- Scenario 4: Withdrawal exceeding account balance ---");
        atm.insertCard(user.getCard());
        atm.authenticatePin(user.getCard(), pin);
        atm.selectOperation(user.getCard(), TransactionType.CASH_WITHDRAWAL);
        atm.cashWithdrawal(user.getCard(), 1000000);

        System.out.println("\n--- Scenario 5: Amount not representable with available notes ---");
        ATM lowDenomAtm = new ATM(2000, 1, 0, 0, new IdleState());
        lowDenomAtm.insertCard(user.getCard());
        lowDenomAtm.authenticatePin(user.getCard(), pin);
        lowDenomAtm.selectOperation(user.getCard(), TransactionType.CASH_WITHDRAWAL);
        lowDenomAtm.cashWithdrawal(user.getCard(), 300);

        System.out.println("\nAccount balance after failed dispense (should be unchanged): " + bankAccount.getBalance());
        System.out.println("Low-denom ATM state (notes should be unchanged): " + lowDenomAtm);
    }
}
