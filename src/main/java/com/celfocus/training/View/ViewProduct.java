package com.celfocus.training.View;

import com.celfocus.training.business.exception.DeleteException;
import com.celfocus.training.business.exception.FindException;
import com.celfocus.training.business.exception.SaveException;
import com.celfocus.training.controller.IProductController;
import com.celfocus.training.controller.dtos.ProductDTO;

import java.util.List;

public class ViewProduct {

    IProductController productController;

    public ViewProduct(IProductController productController) {
        this.productController = productController;
    }

    public void saveProduct(ProductDTO productDTO) {
        try {

            this.productController.createProduct(productDTO);

        } catch (SaveException e) {
            e.printStackTrace();
        }
    }

    public void printProducts(){
        List<ProductDTO> allProductDTO = productController.getAllProductDTO();

        System.out.println();
        System.out.println("All Products");
        System.out.println("----------------");

        for (ProductDTO productDTO : allProductDTO) {
            System.out.println(productDTO);
        }

        System.out.println("----------------");
        System.out.println();
    }

    public String showUser(TypeFile typeFile, ProductDTO productDTO) {
        return "";
    }

    private String buildFileProductXml(ProductDTO productDTO) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>")
                .append("<productName>").append(productDTO.getProductName()).append("<productName>")
                .append("<amount>").append(productDTO.getAmount()).append("<amount>");


        return stringBuilder.toString();
    }

    private String buildFileProductHtml(ProductDTO productDTO) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append("<div>")
                .append("<h1>Product</h1>")
                .append("<span>").append(productDTO.getProductName()).append("<span>")
                .append("<span>").append(productDTO.getAmount()).append("<span>")
                .append("</div>");

        return stringBuilder.toString();
    }

    public void updateUser(ProductDTO productDTO) {
        try {
            productController.updateProduct(productDTO);
        } catch (SaveException e) {
            e.printStackTrace();
        } catch (FindException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(ProductDTO productDTO) {
        try {
            productController.deleteProduct(productDTO);
        } catch (DeleteException e) {
            e.printStackTrace();
        }
    }

}
