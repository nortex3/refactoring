package com.celfocus.training.main;

import com.celfocus.training.logging.ConsoleLogger;
import com.celfocus.training.persistence.ShoppingCartRepository;

public class RemoveItemToShoppingCartMain {
    public static void main(String[] args) {
        ConsoleLogger consoleLogger = new ConsoleLogger();

        ShoppingCartRepository shoppingCartRepository = new ShoppingCartRepository();
        shoppingCartRepository.removeItemToUserShoppingCart("Anna","tennis");
    }


}
