package com.eshop.dao.jdbc;

import com.eshop.dao.entities.Order;
import com.eshop.dao.entities.OrderEntry;
import com.eshop.dao.entities.Product;
import com.eshop.dao.entities.User;
import com.eshop.service.DataSource;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JDBCOrderDAOTest {

    private JDBCOrderDAO orderDAO;

    @Before
    public void setUp() throws Exception {
        orderDAO = JDBCOrderDAO.getInstance();
    }

    @Test
    public void testCreateItem() throws Exception {

        Product product = new Product(1, "Apple", "7plus", "X120",50., 10, "Mobile");
        Product product1 = new Product(2, "Google", "pixel", "x125",50., 10, "Mobile");

        OrderEntry entry = new OrderEntry();
        entry.setPrice(50.);
        entry.setProduct(product);
        entry.setQuantity(1);

        OrderEntry entry1 = new OrderEntry();
        entry1.setPrice(50.);
        entry1.setProduct(product1);
        entry1.setQuantity(1);

        List<OrderEntry> entries = new ArrayList<>();
        entries.add(entry);
        entries.add(entry1);

        Order order = new Order();
        order.setTotalPrice(100.);
        order.setUser(User.newBuilder().setId(1).build());
        order.setEntries(entries);


        boolean result = orderDAO.addNew(order);
        assertTrue(result);
    }

}