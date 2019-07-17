package com.celfocus.training.controller;

import com.celfocus.training.business.exception.DeleteException;
import com.celfocus.training.business.exception.FindException;
import com.celfocus.training.business.exception.SaveException;
import com.celfocus.training.controller.dtos.UserDTO;

import java.util.List;

public interface IUserController {

    List<UserDTO> getAllUserDTO();
    UserDTO findUserByUsername(UserDTO userDTOFromView);
    UserDTO createUser(UserDTO userDTOFromView) throws SaveException;
    boolean deleteUser(UserDTO userDTOFromView) throws DeleteException;
    boolean updateUser(UserDTO userDTOFromView) throws SaveException, FindException;
}
