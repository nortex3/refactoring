package com.celfocus.training.util;

import com.celfocus.training.entity.ItemInfo;
import com.celfocus.training.entity.ShoppingCartItem;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ShoppingCartItemUtil {
    private ShoppingCartItemUtil() {
    }

    public static Optional<ShoppingCartItem> findCartItem(String name, List<ShoppingCartItem> items) {
        return Optional.ofNullable(items)
                       .orElseGet(Collections::emptyList).stream()
                       .filter(cart -> name != null && Optional.ofNullable(cart.getItemInfo())
                                                               .map(ItemInfo::getName)
                                                               .filter(name::equals)
                                                               .isPresent())
                       .findFirst();
    }
}
