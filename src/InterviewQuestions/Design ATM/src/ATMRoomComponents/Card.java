package ATMRoomComponents;

public class Card {
    private int cardNumber;
    private int cvv;
    private int expiryDate;
    private int holderName;
    private int pin;
    private UserBankAccount bankAccount;

    public Card(int cardNumber, int cvv, int expiryDate, int holderName, int pin, UserBankAccount bankAccount) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.holderName = holderName;
        this.pin = pin;
        this.bankAccount = bankAccount;
    }

    public boolean isCorrectPINEntered(int pin) {
        return pin == this.pin;
    }

    public int getBankBalance() {
        return bankAccount.balance;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(int expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getHolderName() {
        return holderName;
    }

    public void setHolderName(int holderName) {
        this.holderName = holderName;
    }

    public UserBankAccount getBankAccount() {
        return bankAccount;
    }

    public void deductBankBalance(int amount) {
        bankAccount.withdrawBalance(amount);
    }

    public void setBankAccount(UserBankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
