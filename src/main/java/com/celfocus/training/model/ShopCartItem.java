package com.celfocus.training.model;

public class ShopCartItem extends AbstractModel {

    private Product product;
    private Discount discount;
    private int quantity;

    public ShopCartItem(int id, Product product, Discount discount, int quantity) {
        super(id);
        this.product = product;
        this.discount = discount;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
