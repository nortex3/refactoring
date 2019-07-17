package com.celfocus.training.controller.dtos;

public class ProductDTO implements IGenericDTO {

    private int id;
    private String productName;
    private double amount;

    public ProductDTO() {}

    public ProductDTO(int id) {
        this.id = id;
    }

    public ProductDTO(String productName, double amount) {
        this.productName = productName;
        this.amount = amount;
    }

    public ProductDTO(int id, String productName, double amount) {
        this.id = id;
        this.productName = productName;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
