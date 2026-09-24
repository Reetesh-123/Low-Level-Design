package ATMRoomComponents;

public class User {

    UserBankAccount bankAccount;
    Card card;

    public UserBankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(UserBankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public User(UserBankAccount bankAccount, Card card) {
        this.bankAccount = bankAccount;
        this.card = card;
    }
}
