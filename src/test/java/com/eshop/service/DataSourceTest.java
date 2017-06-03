package com.eshop.service;

import com.eshop.dao.entities.Laptop;
import com.eshop.dao.entities.Product;
import com.eshop.dao.entities.User;
import com.eshop.dao.jdbc.JDBCLaptopsDAO;
import com.eshop.dao.jdbc.JDBCMobilesDAO;
import com.eshop.dao.jdbc.JDBCUserDAO;
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
        connection.close();
    }

    @Test
    public void createAndFindUser() throws Exception {
        JDBCUserDAO userDao = JDBCUserDAO.getInstance();
        User user = new User(3, "Vasya", "Pupkin", "12345", 0,
                2, "12345", "email22@email.com", true);
        userDao.addNew(user);
        User userToFind = userDao.findEntity("email22@email.com");
        assertNotNull(userToFind);
    }

    //@Test
    public void createAndFindPhone() throws Exception {
        JDBCMobilesDAO jdbcMobilesDAO = JDBCMobilesDAO.getInstance();
        Product product = new Product(null, "Samsung", "ABC", "123XYZ", 1000, 1, "Black");
        jdbcMobilesDAO.addNew(product);
        Product productToFind = jdbcMobilesDAO.findEntity("123XYZ");
        assertNotNull(productToFind);
    }

    //@Test
    public void createAndFindLaptop() throws Exception {
        JDBCLaptopsDAO jdbcLaptopsDAO = JDBCLaptopsDAO.getInstance();
        Laptop laptop = new Laptop(null, "Samsung", "ABC", "123XYZ", 1000, 1);
        jdbcLaptopsDAO.addNew(laptop);
        Laptop laptopToFind = jdbcLaptopsDAO.findEntity("123XYZ");
        assertNotNull(laptopToFind);
    }

    //@Test
    public void deleteLaptop() throws Exception {
        JDBCLaptopsDAO jdbcLaptopsDAO = JDBCLaptopsDAO.getInstance();
        assertTrue(jdbcLaptopsDAO.delete("Z2Z93ES"));
    }
}