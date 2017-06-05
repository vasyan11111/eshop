package com.eshop.service;

import com.eshop.dao.entities.User;
import com.eshop.dao.jdbc.JDBCUserDAO;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

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
        connection.close();
    }

    @Test
    public void createAndFindUser() throws Exception {
       /* JDBCUserDAO userDao = JDBCUserDAO.getInstance();
        User user = new User();
        userDao.addNew(user);
        User userToFind = userDao.findEntity("email22@email.com");
        assertNotNull(userToFind);*/
    }
}