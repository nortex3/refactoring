package com.celfocus.training.saver;

import com.celfocus.training.business.ItemInfo;
import com.celfocus.training.business.ShoppingCart;
import com.celfocus.training.business.ShoppingCartItem;
import com.celfocus.training.business.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Saver {

    private static final List<User> users = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static final List<ItemInfo> items = new ArrayList<>();

    private User updateUser(User user, Date birthDate, boolean adult){

        int userIndexInArray = users.indexOf(user);

        user.setBirthDate(birthDate);
        user.setAdult(adult);
        ShoppingCart shoppingCart = null;

        for (ShoppingCart tempShoppingCart : shoppingCarts) {
            String tempUserName = tempShoppingCart.getUser().getName();
            if (tempUserName.equals(user.getName())) {
                shoppingCart = tempShoppingCart;
                break;
            }
        }

        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart(user);
            shoppingCarts.add(shoppingCart);
        }

        // TODO verify if it works
        users.add(userIndexInArray, user);

        return user;
    }

    private User createUser(String name, Date birthDate, boolean adult){
        User user = new User();
        user.setBirthDate(birthDate);
        user.setName(name);
        user.setAdult(adult);
        users.add(user);
        ShoppingCart shoppingCart = new ShoppingCart(user);
        shoppingCarts.add(shoppingCart);
        return user;
    }

    public User saveOrUpdateUser(String name, Date birthDate, boolean adult) {

        if (name == null || birthDate == null) {
            throw new IllegalArgumentException("Income cannot be null");
        }

        User user = findUserByName(name);

        if (user != null) {
            return updateUser(user, birthDate, adult);
        }else {
            return createUser(name, birthDate, adult);
        }
    }

    private User findUserByName(String name) {

        if (name == null) {
            throw new IllegalArgumentException("Income cannot be null");
        }

        User user = null;

        for (User tempUser : users) {
            if (name.equals(tempUser.getName())) {
                user = tempUser;
                break;
            }
        }
        return user;
    }

    public ItemInfo findItemByName(String name) {

        if (name == null) {
            throw new IllegalArgumentException("Income cannot be null");
        }

        ItemInfo itemInfo = null;

        for (ItemInfo item : items) {
            if (name.equals(item.getName())) {
                itemInfo = item;
                break;
            }
        }
        return itemInfo;
    }

    public void deleteUser(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Income cannot be null");
        }
        User userFound = null;
        for (User user : users) {
            if (name.equals(user.getName())) {
                userFound = user;
                break;
            }
        }
        if (userFound != null) {
            users.remove(userFound);
        }
    }

    private ShoppingCart findShoppingCartByUser(User user) {

        ShoppingCart shoppingCart = null;
        for (ShoppingCart tempShoppingCart : shoppingCarts) {
            if (user.equals(tempShoppingCart.getUser())) {
                shoppingCart = tempShoppingCart;
                break;
            }
        }
        return shoppingCart;
    }

    private ShoppingCartItem findShoppingCartItemInShoppingCart(ShoppingCart shoppingCart, String nameItem){

        ShoppingCartItem shoppingCartItem = null;

        for (ShoppingCartItem tempShoppingCartItem : shoppingCart.getItens()) {
            if (nameItem.equals(tempShoppingCartItem)) {
                shoppingCartItem = tempShoppingCartItem;
                break;
            }
        }
        return shoppingCartItem;
    }

    private ItemInfo findItemInfoByItemName (String nameItem) {

        ItemInfo itemInfo = null;

        for (ItemInfo tempItem : items) {
            if (nameItem.equals(tempItem.getName())) {
                itemInfo = tempItem;
                break;
            }
        }
        return itemInfo;
    }


    public void addItemToCart(String userName, String nameItem, int quantity) {

        if (userName == null || nameItem == null) {
            throw new IllegalArgumentException("Income cannot be null");
        }else if(quantity <0){
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        User user = findUserByName(userName);

        if(user == null){
            throw new IllegalArgumentException("User is invalid");
        }

        ShoppingCart shoppingCart = findShoppingCartByUser(user);

        if(shoppingCart == null){
            throw new IllegalArgumentException("User doesn´t have a shopping cart");
        }

        ShoppingCartItem shoppingCartItem = findShoppingCartItemInShoppingCart(shoppingCart, nameItem);


        if (shoppingCartItem != null) {
            shoppingCartItem.addQuantity(quantity);

        } else {

            ItemInfo itemInfo = findItemInfoByItemName(nameItem);

            if(itemInfo == null){
                throw new IllegalArgumentException("Item Info not found");
            }

            shoppingCartItem = new ShoppingCartItem(itemInfo, quantity, user.getDiscount());

            // TODO add to cart again
        }
    }

    public void removeItemFromCart(String userName, String nameItem) {

        if (userName == null || nameItem == null) {
            throw new IllegalArgumentException("Income cannot be null");
        }

        User user = findUserByName(userName);

        if(user == null){
            throw new IllegalArgumentException("User is invalid");
        }

        ShoppingCart shoppingCart = findShoppingCartByUser(user);

        if(shoppingCart == null){
            throw new IllegalArgumentException("User doesn´t have a shopping cart");
        }

        ShoppingCartItem shoppingCartItem = findShoppingCartItemInShoppingCart(shoppingCart, nameItem);

        if (shoppingCartItem != null) {
            shoppingCart.removeItem(shoppingCartItem);
        }

        // TODO add to cart again
    }

    public void createItem(String itemName, double price) {

        ItemInfo itemInfo = null;
        for (ItemInfo tempItemInfo : items) {
            if (itemName.equals(tempItemInfo.getName())) {
                itemInfo = tempItemInfo;
                break;
            }
        }

        if (itemInfo == null) {
            itemInfo = new ItemInfo(itemName, price);
            items.add(itemInfo);
        }else{
            throw new IllegalArgumentException("Item already exists");
        }
    }
} 