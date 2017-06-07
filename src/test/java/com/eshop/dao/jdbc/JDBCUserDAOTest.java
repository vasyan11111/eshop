package com.eshop.dao.jdbc;

import com.eshop.dao.entities.User;
import org.junit.Before;
import org.junit.Test;

public class JDBCUserDAOTest {

    private JDBCUserDAO userDAO;

    @Before
    public void setUp() throws Exception {
        userDAO = JDBCUserDAO.getInstance();
    }

    @Test
    public void createUser(){
        User user = User.newBuilder()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setEmail("ivanivanov@gmail.com")
                .setPassword("password")
                .setActive(true)
                .build();
    }

}
