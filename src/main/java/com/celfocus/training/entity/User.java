package com.celfocus.training.entity;

import java.util.Date;
import java.util.Objects;

public class User {
    private String name;
    private Date birthDate;
    private boolean isOlder;

    public User(String name, Date birthDate, boolean isOlder) {
        this.name = name;
        this.birthDate = birthDate;
        this.isOlder = isOlder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isOlder() {
        return isOlder;
    }

    public void setOlder(boolean older) {
        isOlder = older;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return isOlder == user.isOlder &&
            Objects.equals(name, user.name) &&
            Objects.equals(birthDate, user.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate, isOlder);
    }
}
