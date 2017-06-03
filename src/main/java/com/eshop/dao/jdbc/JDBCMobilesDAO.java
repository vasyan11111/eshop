package com.eshop.dao.jdbc;


import com.eshop.dao.MobilesDAO;
import com.eshop.dao.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCMobilesDAO extends MobilesDAO {

    private static final String ID = "id";
    private static final String COMPANY = "company";
    private static final String MODEL = "model";
    private static final String SERIES = "series";
    private static final String PRICE = "price";
    private static final String STOCK = "stock";
    private static final String PRODUCT_TYPE = "product_type";

    private static volatile JDBCMobilesDAO instance;

    private JDBCMobilesDAO() {
    }

    public static JDBCMobilesDAO getInstance() {
        if (instance == null) {
            synchronized (JDBCMobilesDAO.class) {
                if (instance == null)
                    instance = new JDBCMobilesDAO();
            }
        }
        return instance;
    }


    @Override
    public Product findEntity(String series) {
        final String SQL = "SELECT * FROM  Mobile_Phones WHERE series=?";

        try (PreparedStatement statement = getConnection().prepareStatement(SQL)) {
            statement.setString(1, series);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) return new Product(
                    rs.getInt(ID),
                    rs.getString(COMPANY),
                    rs.getString(MODEL),
                    rs.getString(SERIES),
                    rs.getInt(PRICE),
                    rs.getInt(STOCK),
                    rs.getString(PRODUCT_TYPE)
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addNew(Product product) {
        final String SQL = "INSERT INTO Mobile_Phones (id, company, model, series, price, "
                + " amount, color) "
                + "VALUES (NULL, ?, ?, ?, ?, ?, ?)";


        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setString(1, product.getCompany());
            statement.setString(2, product.getModel());
            statement.setString(3, product.getSeries());
            statement.setInt(4, product.getPrice());
            statement.setInt(5, product.getStock());
            statement.setString(6, product.getProductType());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return findEntity(product.getSeries()) != null;
    }

    @Override
    public List<Product> getAll() {

        List<Product> products = new ArrayList<>();
        final String SQL = "SELECT * FROM Product";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt(ID),
                        resultSet.getString(COMPANY),
                        resultSet.getString(MODEL),
                        resultSet.getString(SERIES),
                        resultSet.getInt(PRICE),
                        resultSet.getInt(STOCK),
                        resultSet.getString(PRODUCT_TYPE)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product update(Product product) {
        final String SQL = "UPDATE Mobile_Phones  SET "
                + "company=?, model=?, series=?, price=?, amount=?, color=? WHERE"
                + " id=? ";


        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setString(1, product.getCompany());
            statement.setString(2, product.getModel());
            statement.setString(3, product.getSeries());
            statement.setInt(4, product.getPrice());
            statement.setInt(5, product.getStock());
            statement.setString(6, product.getProductType());
            statement.setInt(7, product.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return findEntity(product.getSeries());
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
    public void sell(Product product, int boughtItemsAmount) {
        if (boughtItemsAmount <= 0) {
            return; //TODO:
        }
        product.setStock(product.getStock() - boughtItemsAmount);
        update(product);
    }
}
