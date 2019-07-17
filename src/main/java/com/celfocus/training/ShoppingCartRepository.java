package com.celfocus.training;

import com.celfocus.training.entity.ItemInfo;
import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.ShoppingCartItem;
import com.celfocus.training.entity.User;
import com.celfocus.training.util.ItemInfoUtil;
import com.celfocus.training.util.ShoppingCartItemUtil;
import com.celfocus.training.util.ShoppingCartUtil;
import com.celfocus.training.util.UserUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCartRepository {
    private static final List<User> users = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static final List<ItemInfo> items = new ArrayList<>();

    public void saveOrUpdate(String name, Date birthDate, boolean isOlder) {
        User user = UserUtil.findUser(name, users).orElseGet(() -> new User(name, birthDate, isOlder));
        ShoppingCart shoppingCart = ShoppingCartUtil.findUserShoppingCart(user, shoppingCarts)
                                                    .orElseGet(() -> new ShoppingCart(user, new ArrayList<>()));

        shoppingCarts.add(shoppingCart);
        users.add(user);
    }

    public void deleteUser(String name) {
        UserUtil.findUser(name, users).ifPresent(users::remove);
    }

    public void incrementShoppingCartItemQuantity(String userName, String itemName, int quantity) {
        User user = UserUtil.findUser(userName, users).orElse(null);
        if (user == null) {
            return;
        }

        ShoppingCart shoppingCart = ShoppingCartUtil.findUserShoppingCart(user, shoppingCarts).orElse(null);
        if (shoppingCart == null) {
            return;
        }

        ShoppingCartItemUtil.findCartItem(itemName, shoppingCart.getShoppingCartItems())
                            .ifPresent(shoppingCartItem -> {
                                int newQuantity = shoppingCartItem.getQuantity() + quantity;
                                shoppingCartItem.setQuantity(newQuantity);
                            });
    }

    public void removeItemFromUserShoppingCart(String userName, String itemName) {
        User user = UserUtil.findUser(userName, users).orElse(null);
        if (user == null) {
            return;
        }

        ShoppingCart shoppingCart = ShoppingCartUtil.findUserShoppingCart(user, shoppingCarts).orElse(null);
        if (shoppingCart == null) {
            return;
        }

        List<ShoppingCartItem> shoppingCartItems = shoppingCart.getShoppingCartItems();
        ShoppingCartItemUtil.findCartItem(itemName, shoppingCartItems)
                            .ifPresent(shoppingCartItems::remove);
    }

    public void addItemInfoIfNotExists(String item, double price) {
        boolean itemExists = ItemInfoUtil.containsItem(item, items);
        if (itemExists) {
            return;
        }

        ItemInfo itemInfo = new ItemInfo(item, price);
        items.add(itemInfo);
    }
} 