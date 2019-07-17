package com.celfocus.training.persistence;

import com.celfocus.training.entities.User;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class UserRepository {

    //add User
    public void saveUser(User userToRegister) {
        if (!userExists(userToRegister.getName())) {
            User user = new User(userToRegister.getName(),userToRegister.getDateOfBirth(),userToRegister.isOlder());
            this.findAll().add(user);
        }
    }

    //update User
    public void updateUser(User userToRegister) {
        if (userExists(userToRegister.getName())) {
            User user = new User(userToRegister.getName(),userToRegister.getDateOfBirth(),userToRegister.isOlder());
            /*ShoppingCart found = null;
            for (ShoppingCart var : shoppingCarts) {
                if (var.user == user) {
                    found = var;
                }
            }
            if (found != null) {
                //do nothing
            } else {
                ShoppingCart s = new ShoppingCart();
                s.user = user;
                shoppingCarts.add(s);
            }*/
        }
    }

    public List<User> findAll() {

        // Employees are kept in memory for simplicity
        User anna = new User("Anna", LocalDate.now(),true);
        User billy = new User("Billy", LocalDate.now(),false);
        User steve = new User("Steve", LocalDate.now(),true);

        return Arrays.asList(anna, billy, steve);
    }

    //delete User
    public void deleteUser(String username) {
        User userFound = this.findUser(username);
        if (userFound == null) {
            return;
        }
        this.findAll().remove(userFound);
    }

    public boolean userExists(String username) {
        return findUser(username) != null;
    }


    public User findUser(String username) {
        List<User> users = this.findAll();

        return users.stream()
                .filter(u -> username.equals(u.getName()))
                .findAny()
                .orElse(null);

    }


}
