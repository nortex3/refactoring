package com.celfocus.training.business.impl;


import com.celfocus.training.model.User;
import com.celfocus.training.business.IUserBusiness;
import com.celfocus.training.business.exception.DeleteException;
import com.celfocus.training.business.exception.SaveException;

import java.util.ArrayList;
import java.util.List;


public class UserBusinessImp extends AbstractOperations<User> implements IUserBusiness {

    private List<User> userList;

    public UserBusinessImp() {
        userList = new ArrayList<>();
    }

    @Override
    public void save(User user) throws SaveException {
        if (existingUser(user)) throw new SaveException("This username already exist.");

        userList.add(user);
    }

    @Override
    public User update(User userWithChange) throws SaveException {
        User userUpdate = null;

        for (User userToUpdate : userList) {
            if (userToUpdate.getUsername().equals(userWithChange.getUsername())) {
                userToUpdate.setMajor(userWithChange.isMajor());
                userToUpdate.setBirthDate(userWithChange.getBirthDate());
                userUpdate = userToUpdate;
                break;
            }
        }

        return userUpdate;

    }

    @Override
    public User find(String username) {

        for (User userIteration : userList) {
            if (userIteration.getUsername().equals(username)) {
                return userIteration;
            }
        }

        return null;
    }

    @Override
    public boolean delete(User user) throws DeleteException {
        if (!existingUser(user)) throw new DeleteException("User don´t exist.");

        return userList.remove(user);
    }

    private boolean existingUser(User user) {
        return userList.contains(user) || existingUsername(user.getUsername());
    }

    private boolean existingUsername(String username) {
        return userList.stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
