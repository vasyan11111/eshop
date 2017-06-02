package com.eshop.dao.jdbc;

import com.eshop.dao.LaptopsDAO;
import com.eshop.dao.entities.Laptop;
import com.eshop.service.DataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCLaptopsDAO extends LaptopsDAO{

    private static final String ID = "id";
    private static final String COMPANY = "company";
    private static final String MODEL = "model";
    private static final String SERIES = "series";
    private static final String PRICE = "price";
    private static final String AMOUNT = "amount";

    private static volatile JDBCLaptopsDAO instance;

    private JDBCLaptopsDAO() throws Exception {
        connection = DataSource.getInstance().getConnection();
    }

    public static JDBCLaptopsDAO getInstance() throws Exception {
        if (instance == null) {
            synchronized (JDBCLaptopsDAO.class){
                if (instance == null)
                    instance = new JDBCLaptopsDAO();
            }
        }
        return instance;
    }

    @Override
    public Laptop findEntity(String series) {
        final String SQL = "SELECT * FROM  Laptops WHERE series=?";

        try (PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, series);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) return new Laptop(
                    rs.getInt(ID),
                    rs.getString(COMPANY),
                    rs.getString(MODEL),
                    rs.getString(SERIES),
                    rs.getInt(PRICE),
                    rs.getInt(AMOUNT)
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addNew(Laptop laptop) {
        final String SQL = "INSERT INTO Laptops (id, company, model, series, price, "
                + " amount) "
                + "VALUES (null, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setString(1, laptop.getCompany());
            statement.setString(2, laptop.getModel());
            statement.setString(3, laptop.getSeries());
            statement.setInt(4, laptop.getPrice());
            statement.setInt(5, laptop.getAmount());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return findEntity(laptop.getSeries()) != null;
    }

    @Override
    public List<Laptop> getAll() {
        List<Laptop> laptops = new ArrayList<>();
        final String SQL = "SELECT * FROM Laptops";
        try (PreparedStatement statement = connection.prepareStatement(SQL)){
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                laptops.add(new Laptop(resultSet.getInt(ID),
                        resultSet.getString(COMPANY),
                        resultSet.getString(MODEL),
                        resultSet.getString(SERIES),
                        resultSet.getInt(PRICE),
                        resultSet.getInt(AMOUNT)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return laptops;
    }

    @Override
    public Laptop update(Laptop laptop) {
        final String SQL = "UPDATE Laptops SET "
                + "company=?, model=?, series=?, price=?, amount=? WHERE"
                + " id=? ";

        try (PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setString(1, laptop.getCompany());
            statement.setString(2, laptop.getModel());
            statement.setString(3, laptop.getSeries());
            statement.setInt(4, laptop.getPrice());
            statement.setInt(5, laptop.getAmount());
            statement.setInt(6, laptop.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return findEntity(laptop.getSeries());
    }

    @Override
    public boolean delete(String series) {
        final String SQL = "DELETE FROM Laptops WHERE series=?";

        try (PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, series);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return findEntity(series) == null;
    }

    @Override
    public void sell(Laptop laptop, int boughtItemsAmount) {
        if(boughtItemsAmount <= 0){
            return; //TODO:
        }
        laptop.setAmount(laptop.getAmount() - boughtItemsAmount);
        update(laptop);
    }
}
