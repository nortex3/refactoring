package com.celfocus.training.business;

import com.celfocus.training.business.exception.DeleteException;
import com.celfocus.training.business.exception.FindException;
import com.celfocus.training.business.exception.SaveException;
import com.celfocus.training.model.User;

import java.util.List;

public interface IUserBusiness {

    List<User> getAllUsers();
    boolean existingUser(User user);
    User findUserByName(String username);
    void saveUser(User user) throws SaveException;
    boolean updateUser(User userWithChange) throws FindException, SaveException;
    boolean deleteUser(User user) throws DeleteException;
}
