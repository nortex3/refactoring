package com.celfocus.training.business;

import com.celfocus.training.saver.Saver;

import java.util.List;

public class ShoppingCart {

    private User user;
    private List<ShoppingCartItem> itens;

    public ShoppingCart(User user, List<ShoppingCartItem> itens) {
        this.user = user;
        this.itens = itens;
    }

    public ShoppingCart(User user) {
        this.user = user;
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

    public void removeItem(ShoppingCartItem cartItem){
        this.getItens().remove(cartItem);
    }
}
