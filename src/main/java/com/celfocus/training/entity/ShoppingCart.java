package com.celfocus.training.entity;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private User user;
    private List<Item> items;

    public ShoppingCart(User user) {
        this.user = user;
        items = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
