package com.celfocus.training.persistence;

import com.celfocus.training.entities.ItemInfo;
import com.celfocus.training.entities.ShoppingCart;
import com.celfocus.training.entities.ShoppingCartItem;
import com.celfocus.training.entities.User;
import com.celfocus.training.enums.Discount;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ShoppingCartRepository {

    private UserRepository userRepository;
    private ShoppingCartItemRepository shoppingCartItemRepository;
    private ItemInfoRepository itemInfoRepository;
    private List<ShoppingCart> shoppingCartList;

    public ShoppingCartRepository() {
        this.userRepository = new UserRepository();
        this.itemInfoRepository = new ItemInfoRepository();
        this.shoppingCartItemRepository = new ShoppingCartItemRepository();
        this.shoppingCartList = this.findAll();
    }


    public boolean addItemToUserShoppingCart(String nameOfUser, String nameOfItem, int quantity) {

        printTitle("#### PRINT BEFORE ADD####");
        printShoppingCart(this.shoppingCartList);

        validateData(nameOfUser, nameOfItem);

        User userFound = userRepository.findUser(nameOfUser);

        ShoppingCart shoppingCart = this.findShoppingCartByUser(nameOfUser);

        ShoppingCartItem shoppingCartItem = this.findShoppingCartItemByShoppingCart(shoppingCart, nameOfItem);

        if (shoppingCartItem == null) {
            addShoppingCartItem(nameOfItem, quantity, userFound, shoppingCart);
            return true;
        }
        setShoppingCartItem(quantity, userFound, shoppingCart, shoppingCartItem);
        return true;

    }

    private void addShoppingCartItem(String nameOfItem, int quantity, User userFound, ShoppingCart shoppingCart) {
        ItemInfo itemInfo = this.itemInfoRepository.findItem(nameOfItem);

        Double discount = this.getDiscountIfUserIsOlderOrOlderLessThan80(userFound);

        //create new ShoppingCartItem
        ShoppingCartItem shoppingCartItemFinal = new ShoppingCartItem(itemInfo,quantity,discount);

        this.addShoppingCartItemInShoppingCart(userFound, shoppingCartItemFinal,shoppingCart);
        printTitle("#### PRINT AFTER ADD####");
        printShoppingCart(this.shoppingCartList);
    }

    private void setShoppingCartItem(int quantity, User userFound, ShoppingCart shoppingCart, ShoppingCartItem shoppingCartItem) {
        this.setQuantityInShoppingCartItem(quantity, shoppingCartItem);
        this.setShoppingCartList(userFound,shoppingCart);
        printTitle("#### PRINT AFTER ADD####");
        printShoppingCart(this.shoppingCartList);
    }

    private void printTitle(String s) {
        System.out.println(s);
    }

    private void validateData(String username, String nameOfItem){
        if(!this.userRepository.userExists(username)) throw new IllegalArgumentException("User doesn´t exist");

        if(!this.userShoppingCartByUser(username)) throw new IllegalArgumentException("ShoppingCart doesn´t exist");

        if(!this.itemInfoRepository.itemExists(nameOfItem)) throw new IllegalArgumentException("Item doesn´t exist");
    }

    private Double getDiscountIfUserIsOlderOrOlderLessThan80(User userFound) {

        if (this.userIsOldLessThan80(userFound)) {
            return Discount.DISCOUNT_LESS_THAN_80.getValue();
        }

        if (this.userIsOlder(userFound)) {
            return Discount.DISCOUNT_GRATHER_THAN_80.getValue();
        }

        return Discount.DISCOUNT_DEFAULT.getValue();
    }

    private boolean userIsOlder(User userFound) {
        return userFound.isOlder();
    }

    private boolean userIsOldLessThan80(User userFound) {
        return userFound.isOlder() && (LocalDate.now().getYear() - userFound.getDateOfBirth().getYear() < 80);
    }

    private void setQuantityInShoppingCartItem(int quantity, ShoppingCartItem shoppingCartItem) {
        shoppingCartItem.setQuantity(shoppingCartItem.getQuantity()+quantity);
    }

    private ShoppingCartItem findShoppingCartItemByShoppingCart(ShoppingCart shoppingCart, String nameItem) {
        return shoppingCart.getItens().stream()
                .filter(item -> item.getItem().getName().equals(nameItem))
                .findAny()
                .orElse(null);
    }

    private boolean userShoppingCartByUser(String username) {
        return findShoppingCartByUser(username) != null;
    }

    private ShoppingCart findShoppingCartByUser(String username){
        List<ShoppingCart> shoppingCartList = this.findAll();

        return shoppingCartList.stream()
                .filter(s -> username.equals(s.getUser().getName()))
                .findAny()
                .orElse(null);
    }

    private void addShoppingCartItemInShoppingCart(User user, ShoppingCartItem shoppingCartItem, ShoppingCart shoppingCart){
        shoppingCart.getItens().add(shoppingCartItem);
        setShoppingCartList(user, shoppingCart);
    }

    private void removeShoppingCartItemInShoppingCart(User user, ShoppingCartItem shoppingCartItem, ShoppingCart shoppingCart){
        shoppingCart.getItens().remove(shoppingCartItem);
        setShoppingCartList(user, shoppingCart);
        printTitle("#### PRINT AFTER ADD####");
        printShoppingCart(this.shoppingCartList);
    }

    private void setShoppingCartList(User user, ShoppingCart shoppingCart) {
        int i = getIndexOfShoppingCartItem(user);
        this.shoppingCartList.set(i,shoppingCart);
    }

    private int getIndexOfShoppingCartItem(User user) {
        return IntStream.range(0, this.shoppingCartList.size())
                .filter(i -> user.equals(this.shoppingCartList.get(i).getUser()))
                .findFirst()
                .orElse(-1);	// return -1 if target is not found
    }

    private List<ShoppingCart> findAll() {

        List<User> userList = this.userRepository.findAll();
        List<ShoppingCartItem> shoppingCartItemList1 = this.shoppingCartItemRepository.findAll();
        List<ShoppingCartItem> shoppingCartItemList2 = this.shoppingCartItemRepository.findAll();

        ShoppingCart shoppingCart1 = new ShoppingCart(userList.get(0),shoppingCartItemList1);
        ShoppingCart shoppingCart2 = new ShoppingCart(userList.get(1), shoppingCartItemList2);

        return Arrays.asList(shoppingCart1, shoppingCart2);
    }

    private void printShoppingCart(List<ShoppingCart> shoppingCartList){
        for(ShoppingCart s : shoppingCartList){
            printTitle(s.toString());
        }
    }

    public boolean removeItemToUserShoppingCart(String userName, String nameItem) {

        printTitle("#### PRINT BEFORE ADD####");
        printShoppingCart(this.shoppingCartList);

        validateData(userName,nameItem);

        User userFound = userRepository.findUser(userName);

        ShoppingCart shoppingCart = this.findShoppingCartByUser(userName);

        ShoppingCartItem shoppingCartItem = this.findShoppingCartItemByShoppingCart(shoppingCart, nameItem);

        if (shoppingCartItem == null) {
            return false;
        }

        this.removeShoppingCartItemInShoppingCart(userFound, shoppingCartItem,shoppingCart);
        return true;

    }
}
