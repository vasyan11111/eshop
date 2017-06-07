package com.eshop.dao.entities;



public class User {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private boolean isAdmin;
    private String phoneNumber;
    private Double cash;
    private boolean isActive;

    private User() {
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

    public Double getCash() {
        return cash;
    }

    public void addCash(int cashAmount){
        this.cash += cashAmount;
    }

    public void withdrawCash(Double cashAmount){
        this.cash = cash - cashAmount;
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

    public void setActive(boolean isActive){
        this.isActive = isActive;
    }

    public boolean isAdmin(){
        return this.isAdmin;
    }

    @Override
    public String toString() {
        return "email = " + email +
                ", first name='" + firstName + '\'' +
                ", last name='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", cash=" + cash +
                ", isActive=" + isActive;
    }

    public static Builder newBuilder() {
        return new User().new Builder();
    }

    public class Builder{

        private Builder(){
            //private constructor
        }

        public Builder setId(Integer id){
            User.this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName){
            User.this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName){
            User.this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email){
            User.this.email = email;
            return this;
        }

        public Builder setPassword(String password){
            User.this.password = password;
            return this;
        }

        public Builder setCash(Double cash){
            User.this.cash = cash;
            return this;
        }

        public Builder setAdmin(boolean isAdmin){
            User.this.isAdmin = isAdmin;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber){
            User.this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setActive(Boolean isActive){
            User.this.isActive = isActive;
            return this;
        }

        public User build(){
            return User.this;
        }
    }
}
