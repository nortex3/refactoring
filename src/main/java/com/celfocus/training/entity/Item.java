package com.celfocus.training.entity;

public class Item {

    private String name;
    private double valor;
    private int quantity;
    private double discount;

    public Item(String name, double valor) {
        this.name = name;
        this.valor = valor;
    }

    public Item(String name, double valor, int quantity) {
        this.name = name;
        this.valor = valor;
        this.quantity = quantity;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
}
