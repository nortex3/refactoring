package com.celfocus.training.business.impl;

import com.celfocus.training.model.User;
import com.celfocus.training.business.UserBusiness;
import com.celfocus.training.business.exception.DeleteException;
import com.celfocus.training.business.exception.SaveException;

import java.util.ArrayList;
import java.util.List;

public class UserBusinessImp implements UserBusiness {

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
    public User find(String username) {

        for (User userIteration: userList) {
            if (userIteration.getUsername().equals(username)){
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

    private boolean existingUsername(String username){
        return userList.stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
