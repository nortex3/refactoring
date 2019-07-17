package com.celfocus.training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItem e ItemInfo
 */
public class Saver {

    private static final List<User> listOfUsers = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static final List<ItemInfo> listOfItems = new ArrayList<>();

    public User saveOrUpdateUser(String name, Date bd, boolean adult) {
        if (isMe(name)) {
            User user = findUser(name);
            user.birthday = bd;
            user.adult = adult;
            ShoppingCart shoppingCart = getShoppingCart(user);
            listOfUsers.add(user);
            return user;
        } else {
            User user = new User();
            user.birthday = bd;
            user.username = name;
            user.adult = adult;
            listOfUsers.add(user);
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.user = user;
            shoppingCart.items = new ArrayList<>();
            shoppingCarts.add(shoppingCart);
            return user;
        }
    }

    private ShoppingCart getShoppingCart(User user) {
        for (ShoppingCart shoppingCart : shoppingCarts) {
            if (shoppingCart.user == user) {
                return shoppingCart;
            }
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.user = user;
        shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }

    private boolean isMe(String name) {
        for (User user : listOfUsers) {
            if (user.username.equals(name)) {
                return true;
            }
        }
        return false;
    }

    private User findUser(String name) {
        for (User user : listOfUsers) {
            if (user.username.equals(name)) {
                return user;
            }
        }
        return null;
    }

    public ItemInfo encontrarItem(String name) {
        ItemInfo itemFound = null;
        for (ItemInfo item : listOfItems) {
            if (item.name.equals(name)) {
                itemFound = item;
            }
        }
        return itemFound;
    }

    public void deleteUserOrNot(String name) {
        User userFound = null;
        for (User user : listOfUsers) {
            if (user.username.equals(name)) {
                userFound = user;
            }
        }
        if (userFound == null) {
        } else {
            listOfUsers.remove(userFound);
        }
    }

    public void aIU(String user, String nameItem, int qt) {
        User userFound = null;
        for (User user1 : listOfUsers) {
            if (user1.username.equals(user)) {
                userFound = user1;
            }
        }

        if (userFound != null) {
            ShoppingCart found = null;
            found = getShoppingCart(userFound);

            if (found != null) {
                ShoppingCartItem scif = null;
                for (ShoppingCartItem s : found.items) {
                    if (s.item.name == nameItem) {
                        scif = s;
                    }
                }

                if (scif != null) {
                    scif.quantity += qt;
                } else {
                    ItemInfo ifo = null;
                    for (ItemInfo item : listOfItems) {
                        if (item.name.equals(nameItem)) {
                            ifo = item;
                        }
                    }

                    if (ifo != null) {
                        ShoppingCartItem s1 = new ShoppingCartItem();
                        s1.item = ifo;
                        s1.quantity = qt;
                        if (userFound.adult == true && (new Date().getYear() - userFound.birthday.getYear() < 80)) {
                            s1.discount = 0.2;
                        } else if (userFound.adult == true) {
                            s1.discount = 0.1;
                        }
                    } else {

                    }

                }
            }
        }
    }

    public void rIU(String user, String nameItem) {
        User userFound = null;
        for (User user1 : listOfUsers) {
            if (user1.username.equals(user)) {
                userFound = user1;
            }
        }

        if (userFound != null) {
            ShoppingCart found = null;
            found = getShoppingCart(userFound);

            if (found != null) {
                ShoppingCartItem scif = null;
                for (ShoppingCartItem s : found.items) {
                    if (s.item.name == nameItem) {
                        scif = s;
                    }
                }

                if (scif != null) {
                    found.items.remove(scif);
                }
            }
        }
    }

    public void citemifnotexists(String arg0, double v) {
        ItemInfo f = null;
        for (ItemInfo i : listOfItems) {
            if (i.name == arg0) {
                f = i;
            }
        }

        if (f != null) {

        } else {
            ItemInfo ift = new ItemInfo();
            ift.name = arg0;
            ift.valor = v;
            listOfItems.add(ift);
        }
    }

    public static class User {

        public String username;

        public Date birthday;

        public boolean adult;

    }

    public static class ShoppingCart {

        public User user;

        public List<ShoppingCartItem> items;
    }

    public static class ShoppingCartItem {

        public ItemInfo item;

        public int quantity;

        public double discount;

    }

    public static class ItemInfo {

        public String name;

        public double valor;
    }

} 
