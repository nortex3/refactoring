package com.celfocus.training.controller;

import com.celfocus.training.business.exception.BusinessException;
import com.celfocus.training.controller.dtos.ProductDTO;
import com.celfocus.training.controller.dtos.ShopCartItemDTO;
import com.celfocus.training.controller.dtos.UserDTO;

import java.util.List;

public interface IShopController {

    List<ShopCartItemDTO> getUserShoppingCardList(UserDTO userDTO) throws BusinessException;
    List<ShopCartItemDTO> addProductToUserShoppingCard(UserDTO userDTO, ProductDTO productDTO) throws BusinessException;
    List<ShopCartItemDTO> removeProductToUserShoppingCard(UserDTO userDTO, ProductDTO productDTO) throws BusinessException;

}
