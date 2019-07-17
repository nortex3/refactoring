package com.celfocus.training.entities;

import java.util.List;

public class ShoppingCart {

    private User user;

    private List<ShoppingCartItem> itens;

    public ShoppingCart(User user, List<ShoppingCartItem> itens) {
        this.user = user;
        this.itens = itens;
    }

    public ShoppingCart() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ShoppingCartItem> getItens() {
        return itens;
    }

    public void setItens(List<ShoppingCartItem> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "user=" + user +
                ", itens=" + itens +
                '}';
    }
}
