package com.celfocus.training.controller.dtos;

public class DiscountDTO {

    private double discount;

    public DiscountDTO() {
    }

    public DiscountDTO(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "DiscountDTO{" +
                "discount=" + discount +
                '}';
    }
}
