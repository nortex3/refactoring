package com.celfocus.training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItem e ItemInfo
 */
public class App {

    private static final List<User> users = new ArrayList<>();
    private static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static final List<ItemInfo> itens = new ArrayList<>();

    public User saveOrUpdateUser(String username, Date birthDate, boolean isSenior) {
        if (userExists(username)) {
            User user = findUser(username);
            user.birthDate = birthDate;
            user.isSenior = isSenior;
            ShoppingCart shoppingCart = findShoppingCart(user).get();

            if (shoppingCart == null) {
                shoppingCart = new ShoppingCart();
                shoppingCart.user = user;
                shoppingCarts.add(user);
            }
            users.add(user);
            return user;
        } else {
            User user = new User();
            user.birthDate = birthDate;
            user.username = username;
            user.isSenior = isSenior;
            users.add(user);
            ShoppingCart s = new ShoppingCart();
            s.user = user;
            s.items = new ArrayList<>();
            shoppingCarts.add(s);
            return user;
        }
    }

    private Optional<ShoppingCart> findShoppingCart(User user) {
        for (ShoppingCart shoppingCart : shoppingCarts) {
            if (shoppingCart.user == user) {
                return Optional.of(shoppingCart);
            }
        }
        return Optional.empty();
    }

    private boolean userExists(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.username.equals(name)) {
                userFound = user;
            }
        }
        return userFound != null;
    }

    private User findUser(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.username.equals(name)) {
                userFound = user;
            }
        }
        return userFound;
    }

    public ItemInfo findItem(String name) {
        ItemInfo itemFound = null;
        for (ItemInfo item : itens) {
            if (item.name.equals(name)) {
                itemFound = item;
            }
        }
        return itemFound;
    }

    public void deleteUser(String name) {
        User userFound = null;
        for (User user : users) {
            if (user.username.equals(name)) {
                userFound = user;
            }
        }
        if (userFound == null) {
            System.out.println("User not found");
        } else {
            users.remove(userFound);
            System.out.println("User" + userFound + "deleted");
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
                for (ShoppingCartItem s : found.items) {
                    if (s.item.name == nameItem) {
                        scif = s;
                    }
                }

                if (scif != null) {
                    scif.qt += qt;
                } else {
                    ItemInfo ifo = null;
                    for (ItemInfo item : itens) {
                        if (item.name.equals(nameItem)) {
                            ifo = item;
                        }
                    }

                    if (ifo != null) {
                        ShoppingCartItem s1 = new ShoppingCartItem();
                        s1.item = ifo;
                        s1.qt = qt;
                        if (userFound.isSenior == true && (new Date().getYear() - userFound.birthDate.getYear() < 80)) {
                            s1.discount = 0.2;
                        } else if (userFound.isSenior
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
        for (ItemInfo i : itens) {
            if (i.name == arg0) {
                f = i;
            }
        }

        if (f != null) {

        } else {
            ItemInfo ift = new ItemInfo();
            ift.name = arg0;
            ift.valor = v;
            itens.add(ift);
        }
    }

} 