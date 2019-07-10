package com.celfocus.training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItem e ItemInfo
 */
public class Saver {

    private static final List<User> users = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static final List<ItemInfo> items = new ArrayList<>();

    public static class User {
        
        public String username; // nome

        public Date birthDate; // data de nascimento

        public boolean isMajor; // se usuário é maior de idade

    }

    public static class ShoppingCart {
        
        public User user;

        public List<ShoppingCartItem> shoppingCartItemList;
    }

    public static class ShoppingCartItem {

        public ItemInfo item;

        public int quantity;

        public double discount;

    }

    public static class ItemInfo {

        public String itemName;

        public double amount;
    }

    public User saveOrUpdateUser(String username, Date birthDate, boolean isMajor) {
        if (isUser(username)) {
            User user = getUser(username);
            user.birthDate = birthDate;
            user.isMajor = isMajor;
            ShoppingCart found = null;
            for (ShoppingCart var : shoppingCarts) {
                if (var.user == user) {
                    found = var;
                }
            }

            if (found != null) {
                //do nothing
            } else {
                ShoppingCart s = new ShoppingCart();
                s.user = user;
                shoppingCarts.add(s);
            }
            users.add(user);
            return user;
        } else {
            User user = new User();
            user.birthDate = birthDate;
            user.username = username;
            user.isMajor = isMajor;
            users.add(user);
            ShoppingCart s = new ShoppingCart();
            s.user = user;
            s.shoppingCartItemList = new ArrayList<>();
            shoppingCarts.add(s);
            return user;
        }
    }

    private boolean isUser(String username) {
        User userFound = null;
        for (User user : users) {
            if (user.username.equals(username)) {
                userFound = user;
            }
        }
        return userFound != null;
    }

    private User getUser(String username) {
        User userFound = null;
        for (User user : users) {
            if (user.username.equals(username)) {
                userFound = user;
            }
        }
        return userFound;
    }

    public ItemInfo foundItem(String username) {
        ItemInfo itemFound = null;
        for (ItemInfo item : items) {
            if (item.itemName.equals(username)) {
                itemFound = item;
            }
        }
        return itemFound;
    }

    public void deleteUserOrNot(String username) {
        User userFound = null;
        for (User user : users) {
            if (user.username.equals(username)) {
                userFound = user;
            }
        }
        if (userFound == null) {
        } else {
            users.remove(userFound);
        }
    }

    public void aIU(String user, String nameItem, int qt) {
        User userFound = null;
        for (User user1 : users) {
            if (user1.username.equals(user)) {
                userFound = user1;
            }
        }

        if (userFound != null) {
            ShoppingCart found = null;
            for (ShoppingCart var : shoppingCarts) {
                if (var.user == userFound) {
                    found = var;
                }
            }

            if (found != null) {
                ShoppingCartItem scif = null;
                for (ShoppingCartItem s : found.shoppingCartItemList) {
                    if (s.item.itemName == nameItem) {
                        scif = s;
                    }
                }

                if (scif != null) {
                    scif.quantity += qt;
                } else {
                    ItemInfo ifo = null;
                    for (ItemInfo item : items) {
                        if (item.itemName.equals(nameItem)) {
                            ifo = item;
                        }
                    }

                    if (ifo != null) {
                        ShoppingCartItem s1 = new ShoppingCartItem();
                        s1.item = ifo;
                        s1.quantity = qt;
                        if ( userFound.isMajor
                 == true && (new Date().getYear() - userFound.birthDate.getYear() < 80) ) {
                            s1.discount = 0.2; 
                        } else if (userFound.isMajor
                 == true) {
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
        for (User user1 : users) {
            if (user1.username.equals(user)) {
                userFound = user1;
            }
        }

        if (userFound != null) {
            ShoppingCart found = null;
            for (ShoppingCart var : shoppingCarts) {
                if (var.user == userFound) {
                    found = var;
                }
            }

            if (found != null) {
                ShoppingCartItem scif = null;
                for (ShoppingCartItem s : found.shoppingCartItemList) {
                    if (s.item.itemName == nameItem) {
                        scif = s;
                    }
                }

                if (scif != null) {
                    found.shoppingCartItemList.remove(scif);
                }
            }
        }
    }

    public void citemifnotexists(String arg0, double v) {
        ItemInfo f = null;
        for (ItemInfo i : items){
            if (i.itemName == arg0) {
                f = i;
            }
        }

        if ( f != null ) {

        } else {
            ItemInfo ift = new ItemInfo();
            ift.itemName = arg0;
            ift.amount = v;
            items.add(ift);
        }
    }

} 