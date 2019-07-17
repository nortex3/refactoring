package com.celfocus.training.user;

import java.time.LocalDate;
import com.celfocus.training.controllers.UserController;
import com.celfocus.training.entities.User;
import com.celfocus.training.util.Utils;

/**
 * User For Frontent
 */
public class UserRequesterFrontend {

    private UserController userController;

    public UserRequesterFrontend() {
        this.userController = new UserController();
    }


    /**
     * Metodo utilizado para retornar o Usuario no formato do frontend solicitado
     * @param type tipo do frontend utilizado
     * @param user usuario que será renderizado
     * @return o texto no formato solicitado com as informarções do user
     *
    public String returnFrontendUser(String type, User user) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>User</h1>"
             + "<span>" + user.getName() + "</span>"
             + "<span>" + user.getDateOfBirth() + "</span>"
             + "<span>" + user.ifuserisolder + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<name> " + user.nameOfUser + "</name>"
                    + "<bd>" + user.bd + "</bd>"
                    + "<older> " + user.ifuserisolder + "</older>";
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
     *
    public String returnFrontendShoppingCart(String type, ShoppingCart shoppingCart) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>ShoppingCart</h1>"
             + "<span> " + shoppingCart.user + "</span>"
             + "<span> " + shoppingCart.itens + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<user> " + shoppingCart.user + "</user>"
                    + "<itens> " + shoppingCart.itens + "</itens>";
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
     *
    public String returnFrontendItem(String type, ItemInfo item) {
        if (type.equals("html")) {
            return "<div>"
             + "<h1>Item</h1>"
             + "<span> " + item.name + "</span>"
             + "<span> " + item.valor + "</span>"
             + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<name> " + item.name + "</name>"
                    + "<valor> " + item.valor + "</valor>";
            } else {
                //do nothing
                return "";
            }
        }
    } */

    /**
     * Cria ou atualiza usuario
     * @param userName
     * @param dateString
     */
    public void createUser(String userName, String dateString) {
        LocalDate date = Utils.dateToLocalDate(dateString);

        boolean isOlder = false;
        if (isOlder(date)) {
            isOlder = true;
        }

        this.userController.saveUser(new User(userName.toUpperCase(), date, isOlder));
    }

    public void updateUser(String userName, String dateString, boolean isOlder) {
        LocalDate date = Utils.dateToLocalDate(dateString);
        this.userController.updateUser(new User(userName.toUpperCase(), date, isOlder));
    }

    private boolean isOlder(LocalDate date) {
        return LocalDate.now().getYear() - date.getYear() >= 65;
    }

    /**
     * Remover Usuario
     */
    public void deleteUser(String username) {
        //Saver saver = new Saver();
        //saver.deleteUserOrNot(arg0);
        this.userController.deleteUser(username);

    }

    /**
     * Adicionar item ao carrinho
     */
    /*public void aitemShopping(String user, String nameItem, int qt) {
        Saver saver = new Saver();
        nameItem = nameItem.toLowerCase().concat("_item");
        saver.aIU(user, nameItem, qt);
    }*/

}