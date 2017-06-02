package com.eshop.dao.entities;


public class User {

    private final Integer id;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final Integer userType;
    private final String phoneNumber;
    private boolean isActive;

    public User(Integer id, String name, String lastName, String password, Integer userType, String phoneNumber, String email, boolean isActive) {
        this.id = id;
        this.email = email;
        this.firstName = name;
        this.lastName = lastName;
        this.password = password;
        this.userType = userType;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUserType() {
        return userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isAdmin(){
        return this.userType == 1;
    }
}
