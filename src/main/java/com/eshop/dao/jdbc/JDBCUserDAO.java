package com.eshop.dao.jdbc;

import com.eshop.dao.UserDAO;
import com.eshop.dao.entities.User;
import com.eshop.service.DataSource;

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

    private JDBCUserDAO() throws Exception {
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

            if (rs.next()) return new User(
                    rs.getInt(ID),
                    rs.getString(FIRST_NAME),
                    rs.getString(LAST_NAME),
                    rs.getString(PASSWORD),
                    rs.getInt(CASH),
                    rs.getInt(USER_TYPE),
                    rs.getString(PHONE_NUMBER),
                    rs.getString(EMAIL),
                    rs.getBoolean(ACTIVE));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();


        final String SQL = "SELECT * FROM Users";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt(ID),
                        resultSet.getString(FIRST_NAME),
                        resultSet.getString(LAST_NAME),
                        resultSet.getString(PASSWORD),
                        resultSet.getInt(CASH),
                        resultSet.getInt(USER_TYPE),
                        resultSet.getString(PHONE_NUMBER),
                        resultSet.getString(EMAIL),
                        resultSet.getBoolean(ACTIVE)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void addToBlackList(String email) {
        User user = findEntity(email);
        user.setActive(false);
        update(user);

        final String SQL = "INSERT INTO Black_List (id, email, firstName, lastName, phoneNumber)" +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setInt(1, user.getId());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getPhoneNumber());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
}
