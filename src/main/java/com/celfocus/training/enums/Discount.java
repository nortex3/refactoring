package com.celfocus.training.enums;

public enum Discount {

    DISCOUNT_DEFAULT("dicount default",0.0),
    DISCOUNT_GRATHER_THAN_80("discount grather than 80", 0.1),
    DISCOUNT_LESS_THAN_80("discount less than 80", 0.2);

    private final String key;
    private final Double value;

    Discount(String key, Double value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }
    public Double getValue() {
        return value;
    }

}
