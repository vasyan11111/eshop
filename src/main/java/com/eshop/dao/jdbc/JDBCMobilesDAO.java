package com.eshop.dao.jdbc;


import com.eshop.dao.MobilesDAO;
import com.eshop.dao.entities.Mobile;
import com.eshop.service.DataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCMobilesDAO extends MobilesDAO {

    private static final String ID = "id";
    private static final String COMPANY = "company";
    private static final String MODEL = "model";
    private static final String SERIES = "series";
    private static final String PRICE = "price";
    private static final String AMOUNT = "amount";
    private static final String COLOR = "color";

    private static volatile JDBCMobilesDAO instance;

    private JDBCMobilesDAO() throws Exception {
    }

    public static JDBCMobilesDAO getInstance() throws Exception {
        if (instance == null) {
            synchronized (JDBCMobilesDAO.class){
                if (instance == null)
                    instance = new JDBCMobilesDAO();
            }
        }
        return instance;
    }


    @Override
    public Mobile findEntity(String series) {
        final String SQL = "SELECT * FROM  Mobile_Phones WHERE series=?";

        try (PreparedStatement statement = getConnection().prepareStatement(SQL)) {
            statement.setString(1, series);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) return new Mobile(
                    rs.getInt(ID),
                    rs.getString(COMPANY),
                    rs.getString(MODEL),
                    rs.getString(SERIES),
                    rs.getInt(PRICE),
                    rs.getInt(AMOUNT),
                    rs.getString(COLOR)
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addNew(Mobile mobile) {
        final String SQL = "INSERT INTO Mobile_Phones (id, company, model, series, price, "
                + " amount, color) "
                + "VALUES (null, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(SQL)) {

            statement.setString(1, mobile.getCompany());
            statement.setString(2, mobile.getModel());
            statement.setString(3, mobile.getSeries());
            statement.setInt(4, mobile.getPrice());
            statement.setInt(5, mobile.getAmount());
            statement.setString(6, mobile.getColor());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return findEntity(mobile.getSeries()) != null;
    }

    @Override
    public List<Mobile> getAll() {

        List<Mobile> mobiles = new ArrayList<>();
        final String SQL = "SELECT * FROM Mobile_Phones";
        try (PreparedStatement statement = getConnection().prepareStatement(SQL)){
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                mobiles.add(new Mobile(resultSet.getInt(ID),
                        resultSet.getString(COMPANY),
                        resultSet.getString(MODEL),
                        resultSet.getString(SERIES),
                        resultSet.getInt(PRICE),
                        resultSet.getInt(AMOUNT),
                        resultSet.getString(COLOR)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mobiles;
    }

    @Override
    public Mobile update(Mobile mobile) {
        final String SQL = "UPDATE Mobile_Phones  SET "
                + "company=?, model=?, series=?, price=?, amount=?, color=? WHERE"
                + " id=? ";

        try (PreparedStatement statement = getConnection().prepareStatement(SQL)) {

            statement.setString(1, mobile.getCompany());
            statement.setString(2, mobile.getModel());
            statement.setString(3, mobile.getSeries());
            statement.setInt(4, mobile.getPrice());
            statement.setInt(5, mobile.getAmount());
            statement.setString(6, mobile.getColor());
            statement.setInt(7, mobile.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return findEntity(mobile.getSeries());
    }

    @Override
    public boolean delete(String series) {
        final String SQL = "DELETE FROM Mobile_Phones WHERE series=?";

        try (PreparedStatement statement = getConnection().prepareStatement(SQL)) {
            statement.setString(1, series);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return findEntity(series) == null;
    }

    @Override
    public void sell(Mobile mobile, int boughtItemsAmount) {
        if (boughtItemsAmount <= 0){
            return; //TODO:
        }
        mobile.setAmount(mobile.getAmount() - boughtItemsAmount);
        update(mobile);
    }
}
