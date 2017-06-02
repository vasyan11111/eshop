package com.eshop.service;

import com.eshop.dao.entities.User;
import com.eshop.dao.jdbc.JDBCUserDao;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class DataSourceTest {

    private DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        dataSource = DataSource.getInstance();
    }

    @Test
    public void getConnection() throws Exception {
        Connection connection = dataSource.getConnection();
        assertNotNull(connection);
    }

    @Test
    public void login() throws Exception {
        JDBCUserDao userDao = JDBCUserDao.getInstance();
        User user = new User(3, "Vasya", "Pupkin", "12345",
                2, "12345", "email22@email.com", true);
        userDao.addNew(user);
        User userToFind = userDao.find("email22@email.com");
        assertNotNull(userToFind);
    }

}