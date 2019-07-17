package com.celfocus.training;

import com.celfocus.training.entity.Item;
import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Saver {

    private static final List<User> userList = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCartList = new ArrayList<>();
    private static final List<Item> itemList = new ArrayList<>();

    private User createUser(String name, LocalDate dateOfBirth, boolean isAdult) {

        User user = new User(name, dateOfBirth, isAdult);
        userList.add(user);

        ShoppingCart shoppingCart = new ShoppingCart(user);
        shoppingCartList.add(shoppingCart);

        return user;

    }

    public User UpdateUser(String name, LocalDate dateOfBirth, boolean isAdult) {

        if (!userExists(name)) {
            return this.createUser(name, dateOfBirth, isAdult);
        }

        User user = getUserByName(name);

        user.setDateOfBirth(dateOfBirth);
        user.setAdult(isAdult);

        if (!shoppingCartExists(user)) {
            ShoppingCart shoppingCart = new ShoppingCart(user);
            shoppingCartList.add(shoppingCart);
        }

        return user;
    }

    public void deleteUser(String name) {
        for (User user : userList) {
            if (name.equals(user.getName())) {
                userList.remove(user);
            }
        }
    }

    public void addItemToBasket(String userName, String itemName, int quantity) {

        if (!userExists(userName)) {
            throw new IllegalArgumentException("Can´t find user");
        }
        User user = this.getUserByName(userName);

        if (!shoppingCartExists(user)) {
            throw new IllegalArgumentException("Can´t find user's shopping cart");
        }
        ShoppingCart userShoppingCart = getShoppingCartByUser(user);

        Item item = getItemShoppingCartByName(userShoppingCart.getItems(), itemName);

        if (item != null) {
            item.addQuantity(quantity);
            return;
        }

        Item itemFromList = findItemByName(itemName);
        if (itemFromList != null) {
            Item newItemToBasket = new Item(itemFromList.getName(), itemFromList.getValor(), quantity);

            if (user.isAdult()) {
                int yearsOfAge = Period.between(user.getDateOfBirth(), LocalDate.now()).getYears();
                newItemToBasket.setDiscount(yearsOfAge < 80 ? 0.2 : 0.1);
            }
        }
    }

    public void removeItemFromBasket(String userName, String itemName) {
        if (!userExists(userName)) {
            throw new IllegalArgumentException("Can´t find user");
        }
        User user = this.getUserByName(userName);

        if (!shoppingCartExists(user)) {
            throw new IllegalArgumentException("Can´t find user's shopping cart");
        }
        ShoppingCart userShoppingCart = getShoppingCartByUser(user);
        removeItemFromShoppingCart(userShoppingCart.getItems(), itemName);
    }

    public void createItem(String itemName, double valor) {

        Item itemFromList = findItemByName(itemName);

        if (itemFromList == null) {
            itemList.add(new Item(itemName, valor));
        }
    }

    private boolean shoppingCartExists(User user) {
        return shoppingCartList.stream().anyMatch(shoppingCart -> user == shoppingCart.getUser());
    }

    private ShoppingCart getShoppingCartByUser(User user) {
        return shoppingCartList.stream().filter(shoppingCart -> shoppingCart.getUser() == user).findFirst().orElse(null);
    }

    private boolean userExists(String name) {
        return userList.stream().anyMatch(user -> name.equals(user.getName()));
    }

    private User getUserByName(String name) {
        return userList.stream().filter(user -> name.equals(user.getName())).findFirst().orElse(null);
    }

    private Item findItemByName(String name) {
        return itemList.stream().filter(item -> name.equals(item.getName())).findFirst().orElse(null);
    }

    private Item getItemShoppingCartByName(List<Item> userShoppingCartItems, String itemName) {
        return userShoppingCartItems.stream().filter(item -> itemName.equals(item.getName())).findFirst().orElse(null);
    }

    private void removeItemFromShoppingCart(List<Item> userShoppingCartItems, String itemName) {
        userShoppingCartItems.stream().filter(item -> itemName.equals(item.getName())).findFirst().ifPresent(userShoppingCartItems::remove);
    }

} 