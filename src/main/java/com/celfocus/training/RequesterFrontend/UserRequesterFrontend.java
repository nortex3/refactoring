package com.celfocus.training.RequesterFrontend;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.celfocus.training.business.ItemInfo;
import com.celfocus.training.business.ShoppingCart;
import com.celfocus.training.business.User;
import com.celfocus.training.saver.Saver;
import com.celfocus.training.util.Utils;


public class UserRequesterFrontend {

    public static final String HTML_TYPE_OUTPUT = "html";
    public static final String XML_TYPE_OUTPUT = "xml";
    public static final String SUFFIX = "_item";

    public String returnFrontendUser(String type, User user) {
        if (type.equals(HTML_TYPE_OUTPUT)) {
            return this.userInfoInHTMLType(user);
        } else if (type.equals(XML_TYPE_OUTPUT)) {
            return this.userInfoInXMLType(user);
        } else {
            return "";
        }
    }

    private String userInfoInHTMLType(User user){
        return "<div>"
                + "<h1>User</h1>"
                + "<span>" + user.getName() + "</span>"
                + "<span>" + user.getBirthDate() + "</span>"
                + "<span>" + user.isAdult() + "</span>"
                + "</div>";
    }

    private String userInfoInXMLType(User user){
        return "<div>"
                + "<h1>User</h1>"
                + "<span>" + user.getName() + "</span>"
                + "<span>" + user.getBirthDate() + "</span>"
                + "<span>" + user.isAdult() + "</span>"
                + "</div>";
    }


    public String returnFrontendShoppingCart(String type, ShoppingCart shoppingCart) {
        if (type.equals(HTML_TYPE_OUTPUT)) {
            return shoppingCartInfoInHTMLType(shoppingCart);
        } else if (type.equals(XML_TYPE_OUTPUT)) {
            return shoppingCartInfoInXMLType(shoppingCart);
        } else {
            return "";
        }
    }
    private String shoppingCartInfoInHTMLType(ShoppingCart shoppingCart){
        return "<div>"
                + "<h1>ShoppingCart</h1>"
                + "<span> " + shoppingCart.getUser() + "</span>"
                + "<span> " + shoppingCart.getItens() + "</span>"
                + "</div>";
    }

    private String shoppingCartInfoInXMLType(ShoppingCart shoppingCart){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                + "<RequesterFrontend> " + shoppingCart.getUser() + "</RequesterFrontend>"
                + "<itens> " + shoppingCart.getItens() + "</itens>";
    }


    public String returnFrontendItem(String type, ItemInfo item) {
        if (type.equals(HTML_TYPE_OUTPUT)) {
            return itemInfoInHTMLType(item);
        } else if (type.equals(XML_TYPE_OUTPUT)) {
            return itemInfoInXMLType(item);
        } else {
            return "";
        }

    }

    private String itemInfoInHTMLType(ItemInfo item){
        return "<div>"
                + "<h1>Item</h1>"
                + "<span> " + item.getName() + "</span>"
                + "<span> " + item.getPrice() + "</span>"
                + "</div>";
    }

    private String itemInfoInXMLType(ItemInfo item){
        return "<name> " + item.getName() + "</name>"
                + "<price> " + item.getPrice() + "</price>";
    }

    public void createOrUpdateUser(String userName, String birthDate, String adult) {

        if (userName == null || birthDate == null || adult == null) {
            throw new IllegalArgumentException("Income cannot be null");
        }

        Saver saver = new Saver();

        userName = userName.toUpperCase();

        Date d = Utils.toDate(birthDate, new SimpleDateFormat("dd/mm/yyyy"));
        if (new Date().getYear() - d.getYear() < 65) {
            adult = "false";
        }

        saver.saveOrUpdateUser(userName, Utils.toDate(birthDate, new SimpleDateFormat("dd/mm/yyyy")), "true".equals(adult) ? true : false);
    }

    public void deleteUser(String userName) {

        if (userName == null) {
            throw new IllegalArgumentException("Income cannot be null");
        }

        Saver saver = new Saver();
        saver.deleteUser(userName);
    }

    public void addItemToShoppingCart(String userName, String nameItem, int quantity) {

        if (userName == null || nameItem == null) {
            throw new IllegalArgumentException("Income cannot be null");
        }else if(quantity <0){
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        Saver saver = new Saver();
        nameItem = nameItem.toLowerCase().concat(SUFFIX);
        saver.addItemToCart(userName, nameItem, quantity);
    }
}