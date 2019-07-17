package com.celfocus.training.model;

public class Discount extends AbstractModel {

    private double value;

    public Discount(int id, double discount) {
        super(id);
        this.value = discount;
    }

    public double getValue() {
        return value;
    }
}
