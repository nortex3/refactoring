package com.celfocus.training.business;

public class ItemInfo {

    private String name;
    private double price;

    public ItemInfo(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double value) {
        this.price = value;
    }
}
