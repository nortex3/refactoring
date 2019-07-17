package com.celfocus.training.controller.dtos;

public class ShopCartItemDTO {

    private ProductDTO product;
    private DiscountDTO discount;
    private int quantity;

    public ShopCartItemDTO() {
    }

    public ShopCartItemDTO(ProductDTO product, DiscountDTO discount, int quantity) {
        this.product = product;
        this.discount = discount;
        this.quantity = quantity;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public DiscountDTO getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountDTO discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ShopCartItemDTO{" +
                "product=" + product.getProductName() +
                ", discount=" + discount.getDiscount() +
                ", quantity=" + quantity +
                '}';
    }
}
