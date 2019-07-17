package com.celfocus.training.business;

import java.util.Date;
import java.util.Objects;

public class User {

    private String name;
    private Date birthDate;
    private boolean adult;

    public User(String name, Date birthDate, boolean adult) {
        this.name = name;
        this.birthDate = birthDate;
        this.adult = adult;
    }

    public User() {

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

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public boolean hasLessThanEighYearsOld() {
        return (new Date().getYear() - this.getBirthDate().getYear() < 80);
    }

    public double getDiscount(){

        if (this.isAdult() && this.hasLessThanEighYearsOld()) {
            return 0.2;
        } else if (this.isAdult()) {
            return 0.1;
        }

        return 0.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isAdult() == user.isAdult() &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getBirthDate(), user.getBirthDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBirthDate(), isAdult());
    }
}
