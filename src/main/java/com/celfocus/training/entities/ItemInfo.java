package com.celfocus.training.entities;

public class ItemInfo {

    private String name;

    private double value;

    public ItemInfo(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public ItemInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ItemInfo{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
