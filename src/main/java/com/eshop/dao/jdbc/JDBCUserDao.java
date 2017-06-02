package com.eshop.dao.jdbc;

import com.eshop.dao.UserDAO;
import com.eshop.dao.entities.User;
import com.eshop.service.DataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCUserDao extends UserDAO{

    private static volatile JDBCUserDao instance;

    private JDBCUserDao() throws Exception {
        connection = DataSource.getInstance().getConnection();
    }

    public static JDBCUserDao getInstance() throws Exception {
        if (instance == null) {
            synchronized (JDBCUserDao.class){
                if (instance == null)
                    instance = new JDBCUserDao();
            }
        }
        return instance;
    }

    @Override
    public void addNew(User user) {
        final String SQL = "INSERT INTO Users (id, email, password, firstName, lastName, "
                + " userType, phoneNumber, active) "
                + "VALUES (null, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(SQL)) {

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
    }

    @Override
    public User find(String login) {
        final String SQL = "SELECT * FROM  Users WHERE email=?";

        try (PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) return new User(
                    rs.getInt("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("password"),
                    rs.getInt("userType"),
                    rs.getString("phoneNumber"),
                    rs.getString("email"),
                    rs.getBoolean("active"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();


        final String SQL = "SELECT * FROM Users";
        try (PreparedStatement statement = connection.prepareStatement(SQL)){
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("password"),
                        resultSet.getInt("userType"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("email"),
                        resultSet.getBoolean("active")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void addToBlackList(String email) {
        User user = find(email);
        user.setActive(false);
        update(user);

        final String SQL = "INSERT INTO Black_List (id, email, firstName, lastName, phoneNumber)" +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(SQL)) {

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
    public void update(User user) {

        final String SQL = "UPDATE Users  SET "
                + "firstName=?, lastName=?, password=?, userType=?, phoneNumber=?, email=?, active=? WHERE"
                + " id=? ";

        try (PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getUserType());
            statement.setString(5, user.getPhoneNumber());
            statement.setString(6, user.getEmail());
            statement.setBoolean(7, user.isActive());
            statement.setInt(8, user.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String email) {

        final String SQL = "DELETE FROM Users WHERE email=?";

        try (PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
