package com.celfocus.training.controllers;

import com.celfocus.training.entities.User;
import com.celfocus.training.persistence.UserRepository;

public class UserController {

    private UserRepository userRepository;

    public UserController() {
        this.userRepository = new UserRepository();
    }

    public void deleteUser(String userName) {
        this.userRepository.deleteUser(userName);
    }

    public void updateUser(User user){
        this.userRepository.updateUser(user);
    }

    public void saveUser(User user){
        this.userRepository.saveUser(user);
    }
}
