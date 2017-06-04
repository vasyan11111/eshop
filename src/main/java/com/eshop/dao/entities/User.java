package com.eshop.dao.entities;


public class User {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Integer userType;
    private String phoneNumber;

    private Integer cash;
    private boolean isActive;

    //TODO: Обработать ввод отрицательных значений
    private User() {
//        this.id = id;
//        this.email = email;
//        this.firstName = name;
//        this.lastName = lastName;
//        this.password = password;
//        this.cash = cash;
//        this.userType = userType;
//        this.phoneNumber = phoneNumber;
//        this.isActive = isActive;
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

    public Integer getCash() {
        return cash;
    }

    public void setCash(int cashAmount){
        this.cash = cashAmount;
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

    public boolean isAdmin(){
        return this.userType == 1;
    }

    public static Builder newBuilder() {
        return new User().new Builder();
    }

    public class Builder{

        private Builder(){
            //private constructor
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

        public Builder setCash(Integer cash){
            User.this.cash = cash;
            return this;
        }

        public Builder setUserType(Integer userType){
            User.this.userType = userType;
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
