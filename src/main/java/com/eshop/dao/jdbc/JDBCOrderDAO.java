package com.eshop.dao.jdbc;

import com.eshop.dao.AbstractDAO;
import com.eshop.dao.entities.Order;
import com.eshop.dao.entities.OrderEntry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCOrderDAO extends AbstractDAO<Order, String> {

    private static final String ID = "id";
    private static final String TOTAL_PRICE = "totalPrice";

    private static volatile JDBCOrderDAO instance;

    private JDBCOrderDAO() {
    }

    public static JDBCOrderDAO getInstance() {
        if (instance == null) {
            synchronized (JDBCOrderDAO.class) {
                if (instance == null)
                    instance = new JDBCOrderDAO();
            }
        }
        return instance;
    }

    public List<Order> findAllByUser(String email) {
        final String SQL = "SELECT Orders.totalPrice, Orders.id  FROM Orders " +
                "JOIN Users ON orders.userId = Users.id " +
                "WHERE users.email = ?";

        List<Order> orders = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement oStatement = connection.prepareStatement(SQL)) {
            oStatement.setString(1, email);
            ResultSet rs = oStatement.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt(ID));
                order.setTotalPrice(rs.getDouble(TOTAL_PRICE));
                //order.setEntries();
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<OrderEntry> findEntriesByOrderId(int orderId) {
        final String SQL = "SELECT order_entry.price as e_price FROM Order_entry " +
                "JOIN Product ON Product.id = Order_entry.productId " +
                "WHERE orderId  = ? ";

        List<OrderEntry> entries = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, orderId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                OrderEntry entry = new OrderEntry();
                entry.setPrice(rs.getDouble("e_price"));
                entries.add(entry);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entries;
    }

    @Override
    public Order update(Order entity) {
        final String SQL = "UPDATE Orders SET "
                + " userId=? WHERE totalPrice=?";


        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, entity.getUser().getId());
            statement.setDouble(2, entity.getTotalPrice());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Order findEntity(String id) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean addNew(Order entity) {
        final String SQL_ORDER = "INSERT INTO Orders(userId, totalPrice)" +
                "VALUES (?, ?) ";

        final String SQL_ENTRY = "INSERT INTO Order_Entry(orderId, productId, price, quantity)" +
                "VALUES (?, ?, ?, ?)";

        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_ORDER);
             PreparedStatement statement = connection.prepareStatement(SQL_ENTRY)) {


            connection.setAutoCommit(false);

            preparedStatement.setInt(1, entity.getUser().getId());
            preparedStatement.setDouble(2, entity.getTotalPrice());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            int orderId;
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating order failed, no ID obtained.");
            }

            for (OrderEntry entry : entity.getEntries()) {
                statement.setInt(1, orderId);
                statement.setInt(2, entry.getProduct().getId());
                statement.setDouble(3, entry.getPrice());
                statement.setInt(4, entry.getQuantity());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            ex.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
