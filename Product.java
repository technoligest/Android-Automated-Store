package com.zackrooney.bloopbot;

/**
 * Created by zackrooney on 2016-04-30.
 */
public class Product {

    private int itemId;
    private String itemName;
    private double price;

    public Product(){
        super();
    }

    public Product(int itemId, String itemName, double price) {
        super();
        this.itemId = itemId;
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return this.itemId + ". " + this.itemName + " [$" + this.price + "]";
    }
}
