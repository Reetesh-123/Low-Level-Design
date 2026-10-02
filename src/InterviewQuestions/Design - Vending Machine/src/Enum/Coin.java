package Enum;

public enum Coin {

    FIFTY(50),
    HUNDRED(100),
    THOUSAND(1000);

    private final int value;

    Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
