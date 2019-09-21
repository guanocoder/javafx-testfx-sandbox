package com.guanocoder.javafxapp.models;

import java.util.Date;

public class User {

    private String userName;
    private String firstName;
    private String lastName;
    private Date createdDate;
    private Date lastLoginDate;

    public User(String userName, String firstName, String lastName, Date createdDate, Date lastLoginDate) {
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
