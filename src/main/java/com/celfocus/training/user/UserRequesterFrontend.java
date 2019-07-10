package com.celfocus.training.user;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.celfocus.training.Saver;
import com.celfocus.training.Saver.ItemInfo;
import com.celfocus.training.Saver.ShoppingCart;
import com.celfocus.training.Saver.User;
import com.celfocus.training.util.Utils;

/**
 * User For Frontent
 */
public class UserRequesterFrontend {

    /**
     * Metodo utilizado para retornar o Usuario no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param user usuario que será renderizado
     * @return o texto no formato solicitado com as informarções do user
     */
    public String returnFrontendUser(String type, User user) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>User</h1>"
             + "<span>" + user.username + "</span>"
             + "<span>" + user.birthDate + "</span>"
             + "<span>" + user.isMajor + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<itemName> " + user.username + "</itemName>"
                    + "<birthDate>" + user.birthDate + "</birthDate>"
                    + "<older> " + user.isMajor + "</older>";
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
        if (type.equals("html")) {
            return "<div>"
             + "<h1>ShoppingCart</h1>"
             + "<span> " + shoppingCart.user + "</span>"
             + "<span> " + shoppingCart.shoppingCartItemList + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<user> " + shoppingCart.user + "</user>"
                    + "<shoppingCartItemList> " + shoppingCart.shoppingCartItemList + "</shoppingCartItemList>";
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
    public String returnFrontendItem(String type, ItemInfo item) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>Item</h1>"
             + "<span> " + item.itemName + "</span>"
             + "<span> " + item.amount + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<itemName> " + item.itemName + "</itemName>"
                    + "<amount> " + item.amount + "</amount>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Cria ou atualiza usuario
     * @param arg0
     * @param arg1
     * @param arg2
     */
    public void createOrUpdateUser(String arg0, String arg1, String arg2) {
        Saver saver = new Saver();

        arg0 = arg0.toUpperCase();

        Date d = Utils.toDate(arg1, new SimpleDateFormat("dd/mm/yyyy"));
        if (new Date().getYear() - d.getYear() < 65) {
            arg2 = "false";
        }

        saver.saveOrUpdateUser(arg0, Utils.toDate(arg1, new SimpleDateFormat("dd/mm/yyyy")), arg2.equals("true") ? true : false);
    }

    /**
     * Remover Usuario
     */
    public void deleteUser(String arg0) {
        Saver saver = new Saver();
        saver.deleteUserOrNot(arg0);
    }

    /**
     * Adicionar item ao carrinho
     */
    public void aitemShopping(String user, String nameItem, int qt) {
        Saver saver = new Saver();

        nameItem = nameItem.toLowerCase().concat("_item");

        saver.aIU(user, nameItem, qt);
    }

}