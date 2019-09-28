package com.guanocoder.javafxapp.models;

import java.time.LocalDate;
import java.util.Date;

public class User {

    private String userName;
    private String firstName;
    private String lastName;
    private LocalDate createdDate;
    private LocalDate lastLoginDate;

    public User(String userName, String firstName, String lastName, LocalDate createdDate, LocalDate lastLoginDate) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdDate = createdDate;
        this.lastLoginDate = lastLoginDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDate lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
