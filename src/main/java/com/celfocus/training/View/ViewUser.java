package com.celfocus.training.View;

import com.celfocus.training.business.exception.DeleteException;
import com.celfocus.training.business.exception.FindException;
import com.celfocus.training.business.exception.SaveException;
import com.celfocus.training.controller.IUserController;
import com.celfocus.training.controller.dtos.UserDTO;
import com.celfocus.training.util.Utils;

import java.util.List;

import static com.celfocus.training.util.constant.ConstantNumbers.SIXTY_FIVE_YEAROLD;


public class ViewUser {

    private IUserController userController;

    public ViewUser(IUserController userController) {
        this.userController = userController;
    }

    public void createUser(String username, String year, String month, String day) {
        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(username);
        userDTO.setBirthDate(Utils.parseToDate(year, month, day));

        int userAge = Utils.getAgeFromDate(userDTO.getBirthDate());
        boolean haveLess65YearsOld = userAge > SIXTY_FIVE_YEAROLD;

        userDTO.setMajor(haveLess65YearsOld);

        this.saveUserDto(userDTO);
    }

    private void saveUserDto(UserDTO userDTO) {
        try {

            this.userController.createUser(userDTO);

        } catch (SaveException e) {
            e.printStackTrace();
        }
    }

    public UserDTO getUserDTO(String username) {
        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(username);

        return userController.findUserByUsername(userDTO);
    }

    public void printUsers(){
        List<UserDTO> userDTOList = userController.getAllUserDTO();

        System.out.println();
        System.out.println("All Users");
        System.out.println("----------------");

        for (UserDTO userDTO : userDTOList) {
            System.out.println(userDTO);
        }

        System.out.println("----------------");
        System.out.println();
    }

    public String showUser(TypeFile typeFile, UserDTO userDTO) {
        return "";
    }

    private String buildFileUserXml(UserDTO userDTO) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>")
                .append("<productName>").append(userDTO.getUsername()).append("<productName>")
                .append("<birthDate>").append(userDTO.getBirthDate().toString()).append("<birthDate>")
                .append("<older>").append(userDTO.isMajor()).append("<older>");

        return stringBuilder.toString();
    }

    private String buildFileUserHtml(UserDTO userDTO) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append("<div>")
                .append("<h1>User</h1>")
                .append("<span>").append(userDTO.getUsername()).append("<span>")
                .append("<span>").append(userDTO.getBirthDate().toString()).append("<span>")
                .append("<span>").append(userDTO.isMajor()).append("<span>")
                .append("</div>");

        return stringBuilder.toString();
    }

    public void updateUser(UserDTO userDTO) {
        try {
            userController.updateUser(userDTO);
        } catch (SaveException e) {
            e.printStackTrace();
        } catch (FindException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(UserDTO userDTO) {
        try {
            userController.deleteUser(userDTO);
        } catch (DeleteException e) {
            e.printStackTrace();
        }
    }


}
