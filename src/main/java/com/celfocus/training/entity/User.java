package com.celfocus.training.entity;

import java.time.LocalDate;

public class User {
    private String name;
    private LocalDate dateOfBirth;
    private boolean isAdult;

    public User(String name, LocalDate dateOfBirth, boolean isAdult) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.isAdult = isAdult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }
}
