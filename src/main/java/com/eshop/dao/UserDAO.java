package com.eshop.dao;

import com.eshop.dao.entities.User;

import java.util.List;

public abstract class UserDAO extends AbstractDAO<User, String> {

    public UserDAO() throws Exception {
    }

    public abstract boolean addNew(User user);

    public abstract User findEntity(String login);

    public abstract List<User> getAll();

    public abstract void addToBlackList(String email);

    public abstract User update(User user);

    public abstract boolean delete(String email);

    public abstract void setCash(String email, int cashAmount);

}