package com.celfocus.training.persistence;

import com.celfocus.training.entities.ItemInfo;

import java.util.Arrays;
import java.util.List;

public class ItemInfoRepository {

    public void saveItemInfo(String name, double price) {
        if (!itemExists(name)) {
            ItemInfo itemInfo = new ItemInfo(name, price);
            this.findAll().add(itemInfo);
        }
    }

    public boolean itemExists(String name) {
        return findItem(name) != null;
    }

    public ItemInfo findItem(String name) {
        List<ItemInfo> itemInfoList = this.findAll();

        return itemInfoList.stream()
                .filter(u -> name.equals(u.getName()))
                .findAny()
                .orElse(null);

    }

    public List<ItemInfo> findAll() {

        ItemInfo tennis = new ItemInfo("tennis", 200);
        ItemInfo jeans = new ItemInfo("jeans", 100);
        ItemInfo hat = new ItemInfo("hat", 50);

        return Arrays.asList(tennis, jeans, hat);
    }

}
