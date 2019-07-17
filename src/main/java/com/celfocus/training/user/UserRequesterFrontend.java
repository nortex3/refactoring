package com.celfocus.training.user;

import com.celfocus.training.ShoppingCartRepository;
import com.celfocus.training.entity.ItemInfo;
import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.User;
import com.celfocus.training.util.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

class UserRequesterFrontend {
    private static final String DATE_PATTERN = "dd/mm/yyyy";
    private static final ShoppingCartRepository shoppingCartRepository = new ShoppingCartRepository();

    public String returnFrontendUser(String type, User user) {
        if (type.equals("html")) {
            return "<div>"
                + "<h1>User</h1>"
                + "<span>" + user.getName() + "</span>"
                + "<span>" + user.getBirthDate() + "</span>"
                + "<span>" + user.isOlder() + "</span>"
                + "</div>";
        }

        if (type.equals("xml")) {
            return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                + "<name> " + user.getName() + "</name>"
                + "<bd>" + user.getBirthDate() + "</bd>"
                + "<older> " + user.isOlder() + "</older>";
        }

        return "";
    }

    public String returnFrontendShoppingCart(String type, ShoppingCart shoppingCart) {
        if (type.equals("html")) {
            return "<div>"
                + "<h1>ShoppingCart</h1>"
                + "<span> " + shoppingCart.getUser() + "</span>"
                + "<span> " + shoppingCart.getShoppingCartItems() + "</span>"
                + "</div>";
        }

        if (type.equals("xml")) {
            return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                + "<user> " + shoppingCart.getUser() + "</user>"
                + "<itens> " + shoppingCart.getShoppingCartItems() + "</itens>";
        }

        return "";
    }

    public String returnFrontendItem(String type, ItemInfo item) {
        if (type.equals("html")) {
            return "<div>"
                + "<h1>Item</h1>"
                + "<span> " + item.getName() + "</span>"
                + "<span> " + item.getPrice() + "</span>"
                + "</div>";
        }

        if (type.equals("xml")) {
            return "<name> " + item.getName() + "</name>"
                + "<valor> " + item.getPrice() + "</valor>";
        }

        return "";
    }

    public void createOrUpdateUser(String name, String birthDate) {
        Calendar now = new GregorianCalendar();
        now.setTime(new Date());

        Date date = Util.toDate(birthDate, new SimpleDateFormat(DATE_PATTERN));
        Calendar bDate = new GregorianCalendar();
        bDate.setTime(date);

        boolean isOlder = now.get(Calendar.YEAR) - bDate.get(Calendar.YEAR) >= 65;

        shoppingCartRepository.saveOrUpdate(name.toUpperCase(), date, isOlder);
    }

    public void deleteUser(String user) {
        shoppingCartRepository.deleteUser(user);
    }

    public void addItemToShoppingCart(String user, String item, int quantity) {
        item = item.toLowerCase().concat("_item");

        shoppingCartRepository.incrementShoppingCartItemQuantity(user, item, quantity);
    }

}