package com.eshop.dao.jdbc;

import com.eshop.dao.UserDAO;
import com.eshop.dao.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDAO extends UserDAO {

    private static final String ID = "id";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String USER_TYPE = "userType";
    private static final String CASH = "cash";
    private static final String ACTIVE = "active";

    private static volatile JDBCUserDAO instance;

    private JDBCUserDAO(){
    }

    public static JDBCUserDAO getInstance() throws Exception {
        if (instance == null) {
            synchronized (JDBCUserDAO.class) {
                if (instance == null)
                    instance = new JDBCUserDAO();
            }
        }
        return instance;
    }

    @Override
    public boolean addNew(User user) {
        final String SQL = "INSERT INTO Users (id, email, password, firstName, lastName, "
                + " userType, phoneNumber, active) "
                + "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";


        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, user.getUserType());
            statement.setString(6, user.getPhoneNumber());
            statement.setBoolean(7, true);
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return findEntity(user.getEmail()) != null;
    }

    @Override
    public User findEntity(String email) {
        final String SQL = "SELECT * FROM  Users WHERE email=?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) return User.newBuilder()
                    .setFirstName(rs.getString(FIRST_NAME))
                    .setLastName(rs.getString(LAST_NAME))
                    .setEmail(rs.getString(EMAIL))
                    .setPassword(rs.getString(PASSWORD))
                    .setUserType(rs.getInt(USER_TYPE))
                    .setActive(rs.getBoolean(ACTIVE))
                    .setCash(rs.getInt(CASH))
                    .setPhoneNumber(rs.getString(PHONE_NUMBER))
                    .build();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        User user = User.newBuilder()
                .setFirstName("")
                .setLastName("")
                .setEmail("")
                .setPassword("")
                .setUserType(2)
                .setActive(true)
                .setCash(0)
                .setPhoneNumber("")
                .build();

        final String SQL = "SELECT * FROM Users";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                users.add(User.newBuilder()
                        .setFirstName(rs.getString(FIRST_NAME))
                        .setLastName(rs.getString(LAST_NAME))
                        .setEmail(rs.getString(EMAIL))
                        .setPassword(rs.getString(PASSWORD))
                        .setUserType(rs.getInt(USER_TYPE))
                        .setActive(rs.getBoolean(ACTIVE))
                        .setCash(rs.getInt(CASH))
                        .setPhoneNumber(rs.getString(PHONE_NUMBER))
                        .build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }



    @Override
    public User update(User user) {
        final String SQL = "UPDATE Users  SET "
                + "firstName=?, lastName=?, password=?, cash=?, userType=?, phoneNumber=?, email=?, active=? WHERE"
                + " id=? ";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getCash());
            statement.setInt(5, user.getUserType());
            statement.setString(6, user.getPhoneNumber());
            statement.setString(7, user.getEmail());
            statement.setBoolean(8, user.isActive());
            statement.setInt(9, user.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return findEntity(user.getEmail());
    }

    @Override
    public boolean delete(String email) {

        final String SQL = "DELETE FROM Users WHERE email=?";


        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return findEntity(email) == null;
    }

    @Override
    public void setCash(String email, int cashAmount) {
        if (cashAmount <= 0) {
            return; //TODO:
        }
        User user = findEntity(email);
        user.setCash(cashAmount);
        update(user);
    }

    @Override
    public void findOrders(String email) {

    }
}
