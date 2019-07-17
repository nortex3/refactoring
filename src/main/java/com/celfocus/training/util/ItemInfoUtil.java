package com.celfocus.training.util;

import com.celfocus.training.entity.ItemInfo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ItemInfoUtil {
    private ItemInfoUtil() {
    }

    private static Optional<ItemInfo> findItem(String name, List<ItemInfo> items) {
        return Optional.ofNullable(items)
                       .orElseGet(Collections::emptyList).stream()
                       .filter(item -> name != null && Optional.ofNullable(item.getName())
                                                               .filter(name::equals)
                                                               .isPresent())
                       .findFirst();
    }

    public static boolean containsItem(String name, List<ItemInfo> items) {
        return findItem(name, items).isPresent();
    }
}
