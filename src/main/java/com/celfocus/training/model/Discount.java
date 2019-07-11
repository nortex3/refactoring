package com.celfocus.training.model;

public class Discount extends AbstractModel {

    private Product product;
    private double discount;

    public Discount(int id, Product product, double discount) {
        super(id);
        this.product = product;
        this.discount = discount;
    }

    public Product getProduct() {
        return product;
    }

    public double getDiscount() {
        return discount;
    }
}
