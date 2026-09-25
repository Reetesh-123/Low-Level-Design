package ATMRoomComponents;

public class UserBankAccount {
    int balance;

    public UserBankAccount(int balance){
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void withdrawBalance(int amount){
        balance = balance - amount;
    }

}
