package com.celfocus.training.view;

import com.celfocus.training.business.exception.DeleteException;
import com.celfocus.training.business.exception.FindException;
import com.celfocus.training.business.exception.SaveException;
import com.celfocus.training.controller.IUserController;
import com.celfocus.training.controller.dtos.UserDTO;
import com.celfocus.training.util.Utils;

import java.util.List;
import java.util.logging.Logger;

import static com.celfocus.training.util.constant.ConstantNumbers.SIXTY_FIVE_YEAROLD;
import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;

public class ViewUser {

    private IUserController userController;
    private static Logger logger = Logger.getLogger(ViewUser.class.getName());

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
            logger.log(SEVERE, "Save", e);
        }
    }

    public UserDTO getUserDTO(String username) {
        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(username);

        return userController.findUserByUsername(userDTO);
    }

    public void printUsers(){
        List<UserDTO> userDTOList = userController.getAllUserDTO();

        logger.log(INFO, "All Users");
        logger.log(INFO, "----------------");



        for (UserDTO userDTO : userDTOList) {
            logger.log(INFO, "Object {0}", userDTO);
        }

        logger.log(INFO, "----------------");
    }

    public void updateUser(UserDTO userDTO) {
        try {
            userController.updateUser(userDTO);
        } catch (SaveException | FindException e) {
            logger.log(SEVERE, "", e);
        }
    }

    public void deleteUser(UserDTO userDTO) {
        try {
            userController.deleteUser(userDTO);
        } catch (DeleteException e) {
            logger.log(SEVERE, "", e);
        }
    }


}
