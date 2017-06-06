package com.eshop.dao;

import com.eshop.dao.entities.User;

import java.util.List;

public abstract class UserDAO extends AbstractDAO<User, String> {


    public abstract boolean addNew(User user);

    public abstract User findEntity(String login);

    public abstract List<User> findAll();

    public abstract User update(User user);

    public abstract boolean delete(String email);

    public abstract User addCash(String email, int cashAmount);
}