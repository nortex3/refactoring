package com.celfocus.training.controller.dtos;

import java.util.Date;

public class UserDTO implements IGenericDTO {

    private int id;
    private String username;
    private Date birthDate;
    private boolean major;

    public UserDTO() {
    }

    public UserDTO(int id, String username, Date birthDate, boolean major) {
        this.id = id;
        this.username = username;
        this.birthDate = birthDate;
        this.major = major;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isMajor() {
        return major;
    }

    public void setMajor(boolean major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", birthDate=" + birthDate.toString() +
                ", major=" + major +
                '}';
    }
}
