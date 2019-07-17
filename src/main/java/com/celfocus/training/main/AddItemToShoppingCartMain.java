package com.celfocus.training.main;

import com.celfocus.training.logging.ConsoleLogger;
import com.celfocus.training.persistence.ShoppingCartRepository;

public class AddItemToShoppingCartMain {
    public static void main(String[] args) {
        ConsoleLogger consoleLogger = new ConsoleLogger();

        ShoppingCartRepository shoppingCartRepository = new ShoppingCartRepository();
        shoppingCartRepository.addItemToUserShoppingCart("Anna","jeans", 1);

    }


}
