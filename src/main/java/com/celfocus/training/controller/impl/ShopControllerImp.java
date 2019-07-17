package com.celfocus.training.controller.impl;

import com.celfocus.training.business.IShopBusiness;
import com.celfocus.training.business.exception.BusinessException;
import com.celfocus.training.controller.IShopController;
import com.celfocus.training.controller.dtos.DiscountDTO;
import com.celfocus.training.controller.dtos.ProductDTO;
import com.celfocus.training.controller.dtos.ShopCartItemDTO;
import com.celfocus.training.controller.dtos.UserDTO;
import com.celfocus.training.model.Product;
import com.celfocus.training.model.ShopCartItem;
import com.celfocus.training.model.User;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

import static com.celfocus.training.util.constant.ConstantNumbers.*;

public class ShopControllerImp implements IShopController {

    private IShopBusiness shopBusiness;

    public ShopControllerImp(IShopBusiness shopBusiness) {
        this.shopBusiness = shopBusiness;
    }

    @Override
    public List<ShopCartItemDTO> getUserShoppingCardList(UserDTO userDTO) throws BusinessException {
        User user = this.convertUserDTO(userDTO);

        List<ShopCartItem> userShoppingCardList = this.shopBusiness.getUserShoppingCardList(user);

        return  this.convertListIntoDTO(userShoppingCardList);
    }

    @Override
    public List<ShopCartItemDTO> addProductToUserShoppingCard(UserDTO userDTO, ProductDTO productDTO) throws BusinessException {
        Product product = this.convertProduct(productDTO);
        User user = this.convertUserDTO(userDTO);

        List<ShopCartItem> userShoppingCardList = this.shopBusiness.addProductToUserShoppingCard(product,user);

        return  this.convertListIntoDTO(userShoppingCardList);
    }

    @Override
    public List<ShopCartItemDTO> removeProductToUserShoppingCard(UserDTO userDTO, ProductDTO productDTO) throws BusinessException {
        Product product = this.convertProduct(productDTO);
        User user = this.convertUserDTO(userDTO);

        List<ShopCartItem> userShoppingCardList = this.shopBusiness.removeProductToUserShoppingCard(product,user);

        return this.convertListIntoDTO(userShoppingCardList);
    }

    private List<ShopCartItemDTO> convertListIntoDTO(List<ShopCartItem> userShoppingCardList) {
        List<ShopCartItemDTO> shopCartItemDTOS = new ArrayList<>();

        for (ShopCartItem shopCartItem: userShoppingCardList){
            ProductDTO productDTO = new ProductDTO(ID_TO_DEFAULT);
            DiscountDTO discountDTO = new DiscountDTO();
            BeanUtils.copyProperties(shopCartItem.getProduct(),productDTO);
            BeanUtils.copyProperties(shopCartItem.getDiscount(), discountDTO);
            ShopCartItemDTO shopCartItemDTO = new ShopCartItemDTO(productDTO,discountDTO,shopCartItem.getQuantity());
            shopCartItemDTOS.add(shopCartItemDTO);
        }

        return shopCartItemDTOS;
    }

    private User convertUserDTO(UserDTO userDTO){
        User user = new User(ID_TO_DEFAULT);
        BeanUtils.copyProperties(userDTO,user);

        return user;
    }

    private Product convertProduct(ProductDTO productDTO) {
        Product product = new Product(ID_TO_DEFAULT);
        BeanUtils.copyProperties(productDTO, product);

        return product;
    }

}
