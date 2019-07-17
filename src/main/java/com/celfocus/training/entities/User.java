package com.celfocus.training.entities;

import java.time.LocalDate;
import java.util.Objects;

public class User {

    private String name; // nome

    private LocalDate dateOfBirth; // data de nascimento

    private boolean isOlder; // se usuário é maior de idade

    public User(String name, LocalDate dateOfBirth, boolean isOlder) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.isOlder = isOlder;
    }

    public User() {
    }

    public User(String name) {
        this.name = name;
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

    public boolean isOlder() {
        return isOlder;
    }

    public void setOlder(boolean older) {
        this.isOlder = older;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isOlder == user.isOlder &&
                Objects.equals(name, user.name) &&
                Objects.equals(dateOfBirth, user.dateOfBirth);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, dateOfBirth, isOlder);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", isOlder=" + isOlder +
                '}';
    }
}
