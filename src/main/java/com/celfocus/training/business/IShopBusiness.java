package com.celfocus.training.business;

import com.celfocus.training.business.exception.BusinessException;
import com.celfocus.training.model.Product;
import com.celfocus.training.model.ShopCartItem;
import com.celfocus.training.model.User;

import java.util.List;

public interface IShopBusiness {

    List<ShopCartItem> getUserShoppingCardList(User user) throws BusinessException;

    List<ShopCartItem> addProductToUserShoppingCard(Product product, User user) throws BusinessException;

    List<ShopCartItem> removeProductToUserShoppingCard(Product product, User user) throws BusinessException;

}
