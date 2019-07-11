package com.celfocus.training.model;

public class Product extends AbstractModel {

    private String productName;
    private double amount;

    public Product(int id, String productName, double amount) {
        super(id);
        this.productName = productName;
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public double getAmount() {
        return amount;
    }
}
