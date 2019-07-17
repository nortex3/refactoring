package com.celfocus.training.entities;

public class ShoppingCartItem {

    private ItemInfo item;

    private int quantity;

    private double discount;


    public ShoppingCartItem(ItemInfo item, int quantity, double discount) {
        this.item = item;
        this.quantity = quantity;
        this.discount = discount;
    }

    public ShoppingCartItem() {
    }

    public ItemInfo getItem() {
        return item;
    }

    public void setItem(ItemInfo item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "item=" + item +
                ", quantity=" + quantity +
                ", discount=" + discount +
                '}';
    }
}
