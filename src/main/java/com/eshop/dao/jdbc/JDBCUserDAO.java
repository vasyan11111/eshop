package com.eshop.dao.jdbc;

import com.eshop.command.RegistrationCommand;
import com.eshop.dao.UserDAO;
import com.eshop.dao.entities.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDAO extends UserDAO {

    private static final Logger log = Logger.getLogger(RegistrationCommand.class);

    private static final String ID = "id";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String IS_ADMIN = "admin";
    private static final String CASH = "cash";
    private static final String ACTIVE = "active";

    private static volatile JDBCUserDAO instance;

    private JDBCUserDAO(){
    }

    public static JDBCUserDAO getInstance() {
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
        final String SQL = "INSERT INTO Users ( email, password, firstName, lastName, "
                + " admin, phoneNumber, active, cash) "
                + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";


        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setBoolean(5, user.isAdmin());
            statement.setString(6, user.getPhoneNumber());
            statement.setBoolean(7, true);
            statement.setDouble(8, user.getCash());
            statement.executeUpdate();

        } catch (SQLException ex) {
            log.info(ex);
            return false;
        }

        log.info("New user added to db " + user);

        return true;
    }

    @Override
    public User findEntity(String email) {
        final String SQL = "SELECT * FROM  Users WHERE email=?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) return User.newBuilder()
                    .setId(rs.getInt(ID))
                    .setFirstName(rs.getString(FIRST_NAME))
                    .setLastName(rs.getString(LAST_NAME))
                    .setEmail(rs.getString(EMAIL))
                    .setPassword(rs.getString(PASSWORD))
                    .setAdmin(rs.getBoolean(IS_ADMIN))
                    .setActive(rs.getBoolean(ACTIVE))
                    .setCash(rs.getDouble(CASH))
                    .setPhoneNumber(rs.getString(PHONE_NUMBER))
                    .setId(rs.getInt(ID))
                    .build();

        } catch (SQLException e) {
            log.info(e);
        }
        return null;
    }


    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        final String SQL = "SELECT * FROM Users";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                users.add(User.newBuilder()
                        .setId(rs.getInt(ID))
                        .setFirstName(rs.getString(FIRST_NAME))
                        .setLastName(rs.getString(LAST_NAME))
                        .setEmail(rs.getString(EMAIL))
                        .setPassword(rs.getString(PASSWORD))
                        .setAdmin(rs.getBoolean(IS_ADMIN))
                        .setActive(rs.getBoolean(ACTIVE))
                        .setCash(rs.getDouble(CASH))
                        .setPhoneNumber(rs.getString(PHONE_NUMBER))
                        .build());
            }

        } catch (SQLException e) {
            log.info(e);
        }
        return users;
    }



    @Override
    public User update(User user) {
        final String SQL = "UPDATE Users  SET "
                + "firstName=?, lastName=?, password=?, cash=?, admin=?, phoneNumber=?, active=? WHERE"
                + " email=? ";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPassword());
            statement.setDouble(4, user.getCash());
            statement.setBoolean(5, user.isAdmin());
            statement.setString(6, user.getPhoneNumber());
            statement.setBoolean(7, user.isActive());
            statement.setString(8, user.getEmail());
            statement.executeUpdate();

        } catch (SQLException e) {
            log.info(e);
        }

        log.info("Updated user in db " + user.getEmail());
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
            log.info(e);
            return false;
        }

        log.info("Deleted user from db " + email);
        return true;
    }

    @Override
    public User addCash(String email, int cashAmount) {
        User user = findEntity(email);
        user.addCash(cashAmount);
        user = update(user);
        return user;
    }

    public User withdrawCash(User user, Double cashAmount){
        user.withdrawCash(cashAmount);
        user = update(user);
        return user;
    }

}
