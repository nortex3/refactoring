package com.celfocus.training.util;

import com.celfocus.training.entity.ShoppingCart;
import com.celfocus.training.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ShoppingCartUtil {
    private ShoppingCartUtil() {
    }

    public static Optional<ShoppingCart> findUserShoppingCart(User user, List<ShoppingCart> shoppingCarts) {
        return Optional.ofNullable(shoppingCarts)
                       .orElseGet(Collections::emptyList).stream()
                       .filter(cart -> user != null && Optional.ofNullable(cart.getUser())
                                                               .filter(user::equals)
                                                               .isPresent())
                       .findFirst();
    }
}
