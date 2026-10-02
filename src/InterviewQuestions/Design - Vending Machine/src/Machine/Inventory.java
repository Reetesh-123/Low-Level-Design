package Machine;

import java.util.HashMap;

public class Inventory {
    HashMap<String,Integer> price;
    HashMap<String,Integer>quantity;

    public Inventory(){
        price = new HashMap<>();
        quantity = new HashMap<>();
    }

    public HashMap<String, Integer> getPrice() {
        return price;
    }

    public void setPrice(HashMap<String, Integer> price) {
        this.price = price;
    }

    public HashMap<String, Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(HashMap<String, Integer> quantity) {
        this.quantity = quantity;
    }

    public void addItem(String item, int price, int quantity){
        this.price.put(item,price);
        this.quantity.put(item,quantity);
    }

    public boolean isEmpty() {
        for (int qty : quantity.values()) {
            if (qty > 0) {
                return false;
            }
        }
        return true;
    }
}
