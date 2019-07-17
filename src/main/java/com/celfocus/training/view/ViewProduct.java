package com.celfocus.training.view;

import com.celfocus.training.business.exception.DeleteException;
import com.celfocus.training.business.exception.FindException;
import com.celfocus.training.business.exception.SaveException;
import com.celfocus.training.controller.IProductController;
import com.celfocus.training.controller.dtos.ProductDTO;

import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;

public class ViewProduct {

    private static Logger logger = Logger.getLogger(ViewProduct.class.getName());
    private IProductController productController;

    public ViewProduct(IProductController productController) {
        this.productController = productController;
    }

    public void saveProduct(ProductDTO productDTO) {
        try {

            this.productController.createProduct(productDTO);

        } catch (SaveException e) {
            logger.log(SEVERE, "", e);
        }
    }

    public void printProducts(){
        List<ProductDTO> allProductDTO = productController.getAllProductDTO();

        logger.log(INFO, "All Products");
        logger.log(INFO, "----------------");

        for (ProductDTO productDTO : allProductDTO) {
            logger.log(INFO, "Object {0}", productDTO);
        }

        logger.log(INFO, "----------------");
    }

    public void updateUser(ProductDTO productDTO) {
        try {
            productController.updateProduct(productDTO);
        } catch (SaveException | FindException e) {
            logger.log(SEVERE, "", e);
        }
    }

    public void deleteUser(ProductDTO productDTO) {
        try {
            productController.deleteProduct(productDTO);
        } catch (DeleteException e) {
            logger.log(SEVERE, "", e);
        }
    }

}
