package com.eshop.dao.jdbc;

import com.eshop.dao.AbstractDAO;
import com.eshop.dao.entities.Order;
import com.eshop.dao.entities.OrderEntry;

import java.sql.*;
import java.util.List;

public class JDBCOrderDAO extends AbstractDAO<Order, String> {

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

    public List<Order> findAll() {
        return null;
    }

    @Override
    public Order update(Order entity) {
        return null;
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
            }
            else {
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
