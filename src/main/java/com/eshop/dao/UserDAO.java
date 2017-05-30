package com.eshop.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO extends AbstractDAO {

    private static UserDAO instance;

    private UserDAO() throws Exception {
    }

    public static UserDAO getInstance() throws Exception {
        if (instance == null)
            synchronized (UserDAO.class){
            if (instance == null){
                instance = new UserDAO();
            }
        }
        return instance;
    }

    public boolean find(String login, String password) {
        try {
            try {
                PreparedStatement st = null;
                try {
                    st = getPrepareStatement("SELECT * FROM USERS WHERE LOGIN = ? AND PASSWORD = ?");
                    st.setString(1, login);
                    st.setString(2, password);
                    ResultSet rs = null;
                    try {
                        rs = st.executeQuery();
                        return rs.next();
                    } finally {
                        if (rs != null) {
                            rs.close();
                        }
                    }
                } finally {
                    closePrepareStatement(st);
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Object update(Object entity) {
        return null;
    }

    @Override
    public Object getEntityById(Object id) {
        return null;
    }

    @Override
    public boolean delete(Object id) {
        return false;
    }

    @Override
    public boolean create(Object entity) {
        return false;
    }
}