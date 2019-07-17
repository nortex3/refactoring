package com.celfocus.training.view;

import com.celfocus.training.business.exception.BusinessException;
import com.celfocus.training.controller.IShopController;
import com.celfocus.training.controller.dtos.ProductDTO;
import com.celfocus.training.controller.dtos.ShopCartItemDTO;
import com.celfocus.training.controller.dtos.UserDTO;

import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;

public class ViewShopping {

    private IShopController shopController;
    private static Logger logger = Logger.getLogger(ViewShopping.class.getName());

    public ViewShopping(IShopController shopController) {
        this.shopController = shopController;
    }

    public void getUserShoppingCartList(UserDTO userDTO) {
        try {
            List<ShopCartItemDTO> shopCartItemDTOS = this.shopController.getUserShoppingCardList(userDTO);

            logger.log(INFO, "Shopping Cart");
            logger.log(INFO, "Username: " + userDTO.getUsername());
            logger.log(INFO, "----------------");

            for (ShopCartItemDTO shopCartItemDTO : shopCartItemDTOS) {
                logger.log(INFO, "Object {0}", shopCartItemDTO);
            }

            logger.log(INFO, "----------------");

        } catch (BusinessException e) {
            logger.log(SEVERE, "", e);
        }
    }

    public void addProductShoppingCart(ProductDTO productDTO, UserDTO userDTO) {
        try {
            shopController.addProductToUserShoppingCard(userDTO, productDTO);
        } catch (BusinessException e) {
            logger.log(SEVERE, "", e);
        }
    }
}
