package com.celfocus.training.user;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.celfocus.training.FormatType;
import com.celfocus.training.App;
import com.celfocus.training.ItemInfo;
import com.celfocus.training.ShoppingCart;
import com.celfocus.training.User;
import com.celfocus.training.util.Utils;

public class UserRequesterFrontend {

    private Integer seniorCutOffAge = 65;

    public String returnFrontendUser(String requestedType, User user) {
        if (requestedType.equals(FormatType.HTML)) {
            return "<div>"
                    + "<h1>User</h1>"
                    + "<span>" + user.username + "</span>"
                    + "<span>" + user.birthDate + "</span>"
                    + "<span>" + user.isSenior + "</span>"
                    + "</div>";
        }
        if (requestedType.equals(FormatType.XML)) {
            return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<name> " + user.username + "</name>"
                    + "<bd>" + user.birthDate + "</bd>"
                    + "<older> " + user.isSenior + "</older>";
        }
        return "";

    }

    public String returnFrontendShoppingCart(String requestedType, ShoppingCart shoppingCart) {
        if (requestedType.equals(FormatType.HTML)) {
            return "<div>"
                    + "<h1>ShoppingCart</h1>"
                    + "<span> " + shoppingCart.user + "</span>"
                    + "<span> " + shoppingCart.items + "</span>"
                    + "</div>";
        }
        if (requestedType.equals(FormatType.XML)) {
            return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<user> " + shoppingCart.user + "</user>"
                    + "<items> " + shoppingCart.items + "</items>";
        }
        return "";
    }

    public String returnFrontendItem(String requestedType, ItemInfo item) {
        if (requestedType.equals(FormatType.HTML)) {
            return "<div>"
                    + "<h1>Item</h1>"
                    + "<span> " + item.name + "</span>"
                    + "<span> " + item.price + "</span>"
                    + "</div>";
        }
        if (requestedType.equals(FormatType.XML)) {
            return "<name> " + item.name + "</name>"
                    + "<price> " + item.price + "</price>";
        }
        return "";
    }

    public void createOrUpdateUser(String username, String birthDate) {
        App app = new App();
        boolean userIsSenior;

        username = username.toUpperCase();
        Date formattedBirthDate = Utils.toDate(birthDate, new SimpleDateFormat("dd/mm/yyyy"));
        userIsSenior = UserIsSenior(formattedBirthDate);

        app.saveOrUpdateUser(username, Utils.toDate(birthDate, new SimpleDateFormat("dd/mm/yyyy")), userIsSenior);
    }


    public void deleteUser(String username) {
        App app = new App();

        app.deleteUser(username);
    }


    public void addItem(String user, String nameItem, int quantity) {
        App app = new App();

        nameItem = nameItem.toLowerCase().concat("_item");

        app.addItemToCart(user, nameItem, quantity);
    }


    private boolean UserIsSenior(Date birthDate) {
        return new Date().getYear() - birthDate.getYear() >= seniorCutOffAge;
    }
}