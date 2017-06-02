package com.eshop.dao;

import com.eshop.dao.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class UserDAO implements AutoCloseable{

    protected Connection connection;

    public abstract void addNew(User user);

    public abstract User find(String login);

    public abstract List<User> findAll();

    public abstract void addToBlackList(String email);

    public abstract void update(User user);

    public abstract void deleteUser(String email);

    public abstract void setCash(String email, int cashAmount);

    @Override
    public void close() throws Exception {
        connection.close();
    }
}