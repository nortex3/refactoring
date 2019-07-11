package com.celfocus.training.model;

import java.util.Date;

public class User extends AbstractModel {

    private String username;
    private Date birthDate;
    private boolean isMajor;

    public User(int id, String username, Date birthDate, boolean isMajor){
        super(id);
        this.username = username;
        this.birthDate = birthDate;
        this.isMajor = isMajor;
    }

    public String getUsername() {
        return username;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public boolean isMajor() {
        return isMajor;
    }
}
