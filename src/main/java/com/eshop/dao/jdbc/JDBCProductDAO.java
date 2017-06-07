package com.eshop.dao.jdbc;

import com.eshop.dao.AbstractDAO;
import com.eshop.dao.ProductDAO;
import com.eshop.dao.entities.Product;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCProductDAO extends AbstractDAO<Product, String> implements ProductDAO {

    private static final Logger log = Logger.getLogger(JDBCProductDAO.class);

    private static final String ID = "id";
    private static final String COMPANY = "company";
    private static final String MODEL = "model";
    private static final String SERIES = "series";
    private static final String PRICE = "price";
    private static final String STOCK = "stock";
    private static final String PRODUCT_TYPE = "product_type";

    private static volatile JDBCProductDAO instance;

    private JDBCProductDAO() {
    }

    public static JDBCProductDAO getInstance() {
        if (instance == null) {
            synchronized (JDBCProductDAO.class) {
                if (instance == null)
                    instance = new JDBCProductDAO();
            }
        }
        return instance;
    }


    @Override
    public Product findEntity(String series) {
        final String SQL = "SELECT * FROM  Product WHERE series=?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, series);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) return new Product(
                    rs.getInt(ID),
                    rs.getString(COMPANY),
                    rs.getString(MODEL),
                    rs.getString(SERIES),
                    rs.getDouble(PRICE),
                    rs.getInt(STOCK),
                    rs.getString(PRODUCT_TYPE)
            );

        } catch (SQLException e) {
            log.info(e);
        }
        return null;
    }

    @Override
    public boolean addNew(Product product) {
        final String SQL = "INSERT INTO Product (id, company, model, series, price, "
                + " stock, product_type) "
                + "VALUES (NULL, ?, ?, ?, ?, ?, ?)";


        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setString(1, product.getCompany());
            statement.setString(2, product.getModel());
            statement.setString(3, product.getSeries());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, product.getStock());
            statement.setString(6, product.getProductType());
            statement.executeUpdate();

        } catch (SQLException e) {
            log.info(e);
            return false;
        }

        return true;
    }

    @Override
    public List<Product> findAll() {

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
                        resultSet.getDouble(PRICE),
                        resultSet.getInt(STOCK),
                        resultSet.getString(PRODUCT_TYPE)));
            }

        } catch (SQLException e) {
            log.info(e);
        }
        return products;
    }

    @Override
    public Product update(Product product) {
        final String SQL = "UPDATE Product  SET "
                + "company=?, model=?, series=?, price=?, stock=?, product_type=? WHERE"
                + " id=? ";


        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setString(1, product.getCompany());
            statement.setString(2, product.getModel());
            statement.setString(3, product.getSeries());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, product.getStock());
            statement.setString(6, product.getProductType());
            statement.setInt(7, product.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            log.info(e);
        }
        return findEntity(product.getSeries());
    }

    @Override
    public boolean delete(String series) {
        final String SQL = "DELETE FROM Product WHERE series=?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, series);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.info(e);
            return false;
        }

        return true;
    }

    @Override
    public List<Product> findSpecifiedProduct(String productType) {
        List<Product> products = new ArrayList<>();
        final String SQL = "SELECT * FROM Product WHERE product_type = " + productType;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt(ID),
                        resultSet.getString(COMPANY),
                        resultSet.getString(MODEL),
                        resultSet.getString(SERIES),
                        resultSet.getDouble(PRICE),
                        resultSet.getInt(STOCK),
                        resultSet.getString(PRODUCT_TYPE)));
            }
        } catch (SQLException e) {
            log.info(e);
        }

        return products;
    }

    @Override
    public void sell(Product product, int boughtItemsAmount) {
        product.setStock(product.getStock() - boughtItemsAmount);
        update(product);
    }
}
