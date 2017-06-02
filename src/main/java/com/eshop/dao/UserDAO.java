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

    public abstract User find(int id);

    public abstract User find(String login);

    public abstract List<User> findAll();

    public abstract List<User> findByString(String condition);

    public abstract void update(User user);

    public abstract void delete(int id);

    @Override
    public void close() throws Exception {
        connection.close();
    }
}