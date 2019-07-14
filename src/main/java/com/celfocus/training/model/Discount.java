package com.celfocus.training.model;

public class Discount extends AbstractModel {

    private double discount;

    public Discount(int id, double discount) {
        super(id);
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}
