package com.celfocus.training.user;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.celfocus.training.FormatType;
import com.celfocus.training.App;
import com.celfocus.training.ItemInfo;
import com.celfocus.training.ShoppingCart;
import com.celfocus.training.User;
import com.celfocus.training.util.Utils;

/**
 * User For Frontent
 */
public class UserRequesterFrontend {

    private Integer seniorCutOffAge = 65;


    /**
     * Metodo utilizado para retornar o Usuario no formato do frontend solicitado
     *
     * @param requestedType tipo do frontend utilizado
     * @param user usuario que será renderizado
     * @return o texto no formato solicitado com as informarções do user
     */
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

    /**
     * Metodo utilizado para retornar o Shoppingcart no formato do frontend solicitado
     *
     * @param requestedType         tipo do frontend utilizado
     * @param shoppingCart shoppingCart que será renderizado
     * @return o texto no formato solicitado com as informarções do shoppingCart
     */
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

    /**
     * Metodo utilizado para retornar o Item no formato do frontend solicitado
     *
     * @param requestedType tipo do frontend utilizado
     * @param item item que será renderizado
     * @return o texto no formato solicitado com as informarções do item
     */
    public String returnFrontendItem(String requestedType, ItemInfo item) {
        if (requestedType.equals(FormatType.HTML)) {
            return "<div>"
                    + "<h1>Item</h1>"
                    + "<span> " + item.name + "</span>"
                    + "<span> " + item.valor + "</span>"
                    + "</div>";
        }
        if (requestedType.equals(FormatType.XML)) {
            return "<name> " + item.name + "</name>"
                    + "<valor> " + item.valor + "</valor>";
        }
        return "";


    }

    /**
     * Cria ou atualiza usuario
     *
     * @param username
     * @param birthDate
     * @param
     */
    public void createOrUpdateUser(String username, String birthDate) {
        App app = new App();
        Boolean userIsSenior;
        username = username.toUpperCase();

        Date formattedBirthDate = Utils.toDate(birthDate, new SimpleDateFormat("dd/mm/yyyy"));
        if (!UserIsSenior(formattedBirthDate)) {
            userIsSenior = false;
        }else{
            userIsSenior = true;
        }

        app.saveOrUpdateUser(username, Utils.toDate(birthDate, new SimpleDateFormat("dd/mm/yyyy")), userIsSenior);
    }

    private boolean UserIsSenior(Date date) {
        return new Date().getYear() - date.getYear() < seniorCutOffAge;
    }

    /**
     * Remover Usuario
     */
    public void deleteUser(String username) {
        App app = new App();
        app.deleteUser(username);
    }

    /**
     * Adicionar item ao carrinho
     */
    public void addItem(String user, String nameItem, int qt) {
        App app = new App();

        nameItem = nameItem.toLowerCase().concat("_item");

        app.aIU(user, nameItem, qt);
    }

}