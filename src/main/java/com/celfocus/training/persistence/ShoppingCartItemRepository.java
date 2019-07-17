package com.celfocus.training.persistence;

import com.celfocus.training.entities.ItemInfo;
import com.celfocus.training.entities.ShoppingCartItem;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartItemRepository {

    private ItemInfoRepository itemInfoRepository;

    public ShoppingCartItemRepository() {
        this.itemInfoRepository = new ItemInfoRepository();
    }

    public List<ShoppingCartItem> findAll() {

        List<ItemInfo> itemInfoList = this.itemInfoRepository.findAll();

        ShoppingCartItem shoppingCartItem1 = new ShoppingCartItem(itemInfoList.get(0), 2, 0.0);
        ShoppingCartItem shoppingCartItem2 = new ShoppingCartItem(itemInfoList.get(1), 1, 0.0);

        ArrayList list = new ArrayList();
        list.add(shoppingCartItem1);
        list.add(shoppingCartItem2);

        return list;//Arrays.asList(shoppingCartItem1, shoppingCartItem2);
    }

    public boolean shoppingCartItemExists(String name) {
        return findShoppingCartItem(name) != null;
    }

    public ShoppingCartItem findShoppingCartItem(String name) {
        List<ShoppingCartItem> shoppingCartItemList = this.findAll();

        return shoppingCartItemList.stream()
                .filter(s -> name.equals(s.getItem().getName()))
                .findAny()
                .orElse(null);

    }
}
