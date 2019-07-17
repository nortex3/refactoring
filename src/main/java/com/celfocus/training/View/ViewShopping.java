package com.celfocus.training.View;

import com.celfocus.training.business.exception.BusinessException;
import com.celfocus.training.controller.IShopController;
import com.celfocus.training.controller.dtos.ProductDTO;
import com.celfocus.training.controller.dtos.ShopCartItemDTO;
import com.celfocus.training.controller.dtos.UserDTO;

import java.util.List;

public class ViewShopping {

    private IShopController shopController;

    public ViewShopping(IShopController shopController) {
        this.shopController = shopController;
    }

    public void getUserShoppingCartList(UserDTO userDTO) {
        try {
            List<ShopCartItemDTO> shopCartItemDTOS = this.shopController.getUserShoppingCardList(userDTO);

            System.out.println();
            System.out.println("Shopping Cart");
            System.out.println("Username: " + userDTO.getUsername());
            System.out.println("----------------");

            for (ShopCartItemDTO shopCartItemDTO : shopCartItemDTOS) {
                System.out.println(shopCartItemDTO);
            }

            System.out.println("----------------");
            System.out.println();

        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    public void addProductShoppingCart(ProductDTO productDTO, UserDTO userDTO) {
        try {
            shopController.addProductToUserShoppingCard(userDTO, productDTO);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }
}
