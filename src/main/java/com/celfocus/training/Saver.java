package com.celfocus.training;

import com.celfocus.training.model.Product;
import com.celfocus.training.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItem e Product
 */
public class Saver {

    private static final List<User> USER_LIST = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static final List<Product> items = new ArrayList<>();


    public static class ShoppingCart {
        
        public User user;

        public List<ShoppingCartItem> shoppingCartItemList;
    }

    public static class ShoppingCartItem {

        public Product item;

        public int quantity;

        public double discount;

    }

    public User saveOrUpdateUser(String username, Date birthDate, boolean isMajor) {
        if (isUser(username)) {
            User user = getUser(username);
            user.setBirthDate(birthDate);
            user.setMajor(isMajor);

            // try found the shoppingCardItem
            ShoppingCart shoppingCarFound = null;
            for (ShoppingCart shoppingCart : shoppingCarts) {
                if (shoppingCart.user == user) {
                    shoppingCarFound = shoppingCart;
                }
            }


            if (shoppingCarFound == null) {
                ShoppingCart shoppingCart = new ShoppingCart();
                shoppingCart.user = user;
                shoppingCarts.add(shoppingCart);
            }

            USER_LIST.add(user);
            return user;
        } else {
            User user = new User(USER_LIST.size() +1,username,birthDate,);
            user.birthDate = birthDate;
            user.username = username;
            user.isMajor = isMajor;
            USER_LIST.add(user);
            ShoppingCart s = new ShoppingCart();
            s.user = user;
            s.shoppingCartItemList = new ArrayList<>();
            shoppingCarts.add(s);
            return user;
        }
    }

    private boolean isUser(String username) {
        boolean userFound = false;

        for (User user : USER_LIST) {
            if (user.getUsername().equals(username)) {
                userFound = true;
            }
        }
        return userFound;
    }

    private User getUser(String username) {
        User userFound = null;

        for (User user : USER_LIST) {
            if (user.getUsername().equals(username)) {
                userFound = user;
            }
        }

        return userFound;
    }

    public Product foundItem(String username) {
        Product itemFound = null;
        for (Product item : items) {
            if (item.getProductName().equals(username)) {
                itemFound = item;
            }
        }
        return itemFound;
    }

    public void deleteUserOrNot(String username) {
        User userFound = null;
        for (User user : USER_LIST) {
            if (user.getUsername().equals(username)) {
                userFound = user;
            }
        }
        if (userFound == null) {
        } else {
            USER_LIST.remove(userFound);
        }
    }

    public void addItemUnit(String user, String nameItem, int qt) {
        User userFound = null;
        for (User user1 : USER_LIST) {
            if (user1.getUsername().equals(user)) {
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
                ShoppingCartItem shoppingCartItem = null;
                for (ShoppingCartItem s : found.shoppingCartItemList) {
                    if (s.item.getProductName() == nameItem) {
                        shoppingCartItem = s;
                    }
                }

                if (shoppingCartItem != null) {
                    shoppingCartItem.quantity += qt;
                } else {
                    Product ifo = null;
                    for (Product item : items) {
                        if (item.productName.equals(nameItem)) {
                            ifo = item;
                        }
                    }

                    if (ifo != null) {
                        ShoppingCartItem s1 = new ShoppingCartItem();
                        s1.item = ifo;
                        s1.quantity = qt;
                        if ( userFound.isMajor == true && (new Date().getYear() - userFound.birthDate.getYear() < 80) ) {
                            s1.discount = 0.2; 
                        } else if (userFound.isMajor == true) {
                            s1.discount = 0.1;
                        }
                    } else {

                    }
                    
                }
            }
        }
    }

    public void removeItemUnit(String user, String nameItem) {
        User userFound = null;
        for (User user1 : USER_LIST) {
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
                    if (s.item.productName == nameItem) {
                        scif = s;
                    }
                }

                if (scif != null) {
                    found.shoppingCartItemList.remove(scif);
                }
            }
        }
    }

    public void addProduct(String productName, double amount) {
        Product f = null;
        for (Product i : items){
            if (i.productName == productName) {
                f = i;
            }
        }

        if (f == null) {
            Product ift = new Product();
            ift.productName = productName;
            ift.amount = amount;
            items.add(ift);
        }
    }

} 