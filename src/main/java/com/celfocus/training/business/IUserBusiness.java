package com.celfocus.training.business;

import com.celfocus.training.business.exception.DeleteException;
import com.celfocus.training.business.exception.FindException;
import com.celfocus.training.business.exception.SaveException;
import com.celfocus.training.model.User;

public interface IUserBusiness extends IOperations<User> {

    boolean existingUser(User user);
    User findUserByName(String username);
    void saveUser(User user) throws SaveException;
    boolean updateUser(User userWithChange) throws FindException, SaveException;
    boolean deleteUser(User user) throws DeleteException;
}
