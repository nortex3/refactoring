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
            ShoppingCart shoppingCart = findShoppingCart(user).orElse(null);
            if (shoppingCart == null) createNewShoppingCart(user);
            users.add(user);
            return user;
        } else {
            User user = new User();
            user.username = username;
            user.birthDate = birthDate;
            user.isSenior = isSenior;
            createNewShoppingCart(user);
            users.add(user);
            return user;
        }
    }

    private void createNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.user = user;
        shoppingCart.items = new ArrayList<>();
        shoppingCarts.add(shoppingCart);
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
        ItemInfo itemFound = returnExistingItemInfo(name);
        return itemFound;
    }

    public void deleteUser(String name) {
        User userFound;

        userFound = users.stream().filter(user -> name.equals(user.username)).findAny().orElse(null);

        if (userFound == null) {
            System.out.println("User not found");
        } else {
            users.remove(userFound);
            System.out.println("User" + userFound + "deleted");
        }
    }

    public void addItemToCart(String user, String nameItem, int quantity) {

        User userFound=findUser(user);
        if (userFound == null) {
            System.out.println("User not found");
            return;
        }

        ShoppingCart shoppingCartFound = findShoppingCart(userFound).orElse(new ShoppingCart());

        ShoppingCartItem shoppingCartItemFound = returnExistingShoppingCartItem(nameItem, shoppingCartFound);

        updateShoppingCartItemQuantity(nameItem, quantity, userFound, shoppingCartItemFound);
    }

    private void updateShoppingCartItemQuantity(String nameItem, int quantity, User userFound, ShoppingCartItem shoppingCartItem) {
        if (shoppingCartItem == null) {

            ItemInfo itemInfo = returnExistingItemInfo(nameItem);

            if (itemInfo == null) {
                System.out.println("Item does not exist");
                return;
            } else {
                createShoppingCartItem(quantity, userFound, shoppingCartItem, itemInfo);
            }

        } else {
            shoppingCartItem.quantity += quantity;
        }
    }

    private void createShoppingCartItem(int quantity, User userFound, ShoppingCartItem shoppingCartItem, ItemInfo itemInfo) {
        shoppingCartItem.itemInfo = itemInfo;
        shoppingCartItem.quantity = quantity;
        if (userFound.isSenior == true && (new Date().getYear() - userFound.birthDate.getYear() < 80)) {
            shoppingCartItem.discount = 0.2;
        } else if (userFound.isSenior
                == true) {
            shoppingCartItem.discount = 0.1;
        }
    }

    private ItemInfo returnExistingItemInfo(String nameItem) {
        ItemInfo itemInfo = null;
        for (ItemInfo item : itens) {
            if (item.name.equals(nameItem)) {
                itemInfo = item;
            }
        }
        return itemInfo;
    }

    private ShoppingCartItem returnExistingShoppingCartItem(String nameItem, ShoppingCart shoppingCartFound) {
        ShoppingCartItem shoppingCartItem = null;
        for (ShoppingCartItem cartItem : shoppingCartFound.items) {
            if (cartItem.itemInfo.name == nameItem) {
                shoppingCartItem = cartItem;
            }
        }
        return shoppingCartItem;
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
                    if (s.itemInfo.name == nameItem) {
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
            ift.price = v;
            itens.add(ift);
        }
    }

} 