package com.celfocus.training.user;

import com.celfocus.training.Saver;
import com.celfocus.training.entity.Item;
import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.User;
import com.celfocus.training.util.Utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * User For Frontent
 */
public class UserRequesterFrontend {

    public static final int OLDER_AGE_LIMIT_VALUE = 65;
    Saver saver = new Saver();

    /**
     * Metodo utilizado para retornar o Usuario no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param user usuario que será renderizado
     * @return o texto no formato solicitado com as informarções do user
     */
    public String returnFrontendUser(String type, User user) {
        if ("html".equals(type)) {
            return "<div>"
             + "<h1>User</h1>"
                + "<span>" + user.getName() + "</span>"
                + "<span>" + user.getDateOfBirth() + "</span>"
                + "<span>" + user.isOlder() + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<name> " + user.getName() + "</name>"
                    + "<bd>" + user.getDateOfBirth() + "</bd>"
                    + "<older> " + user.isOlder() + "</older>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Metodo utilizado para retornar o Shoppingcart no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param shoppingCart shoppingCart que será renderizado
     * @return o texto no formato solicitado com as informarções do shoppingCart
     */
    public String returnFrontendShoppingCart(String type, ShoppingCart shoppingCart) {
        if ("html".equals(type)) {
            return "<div>"
             + "<h1>ShoppingCart</h1>"
                + "<span> " + shoppingCart.getUser() + "</span>"
                + "<span> " + shoppingCart.getItems() + "</span>"
             + "</div>";
        } else {
            if ("xml".equals(type)) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<user> " + shoppingCart.getUser() + "</user>"
                    + "<itens> " + shoppingCart.getItems() + "</itens>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Metodo utilizado para retornar o Item no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param item item que será renderizado
     * @return o texto no formato solicitado com as informarções do item
     */
    public String returnFrontendItem(String type, Item item) {
        if ("html".equals(type)) {
            return "<div>"
             + "<h1>Item</h1>"
                + "<span> " + item.getName() + "</span>"
                + "<span> " + item.getValor() + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<name> " + item.getName() + "</name>"
                    + "<valor> " + item.getValor() + "</valor>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Create or update User Update
     * @param userName Name of user
     * @param dateOfBirthUnformatted DOB of User
     */
    public void updateUser(String userName, String dateOfBirthUnformatted) {
        LocalDate formattedDateOfBirth = Utils.toDate(dateOfBirthUnformatted, DateTimeFormatter.ofPattern("dd/mm/yyyy"));
        int yearsOfAge = Period.between(formattedDateOfBirth, LocalDate.now()).getYears();

        saver.UpdateUser(userName.toUpperCase(), Utils.toDate(dateOfBirthUnformatted, DateTimeFormatter.ofPattern("dd/mm/yyyy")),
                         yearsOfAge >= OLDER_AGE_LIMIT_VALUE);
    }

    /**
     * Remove User
     * @param userName Name of user
     */
    public void deleteUser(String userName) {
        saver.deleteUser(userName);
    }

    /**
     * Add item to basket
     * @param userName Name of user
     * @param itemName Name of the item
     * @param quantity Quantity of items
     */
    public void addItemToBasket(String userName, String itemName, int quantity) {
        itemName = itemName.toLowerCase().concat("_item");

        saver.addItemToBasket(userName, itemName, quantity);
    }

}