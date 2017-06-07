package com.eshop.command;

import com.eshop.dao.entities.Product;
import com.eshop.dao.jdbc.JDBCProductDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewProductCommand implements ICommand {

    private static final Logger log = Logger.getLogger(AddNewProductCommand.class);

    private static final String COMPANY = "company";
    private static final String MODEL = "model";
    private static final String SERIES = "series";
    private static final String PRICE = "price";
    private static final String STOCK = "stock";
    private static final String PRODUCT_TYPE = "productType";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String company = request.getParameter(COMPANY);
        String model = request.getParameter(MODEL);
        String series = request.getParameter(SERIES);
        Double price = Double.valueOf(request.getParameter(PRICE));
        Integer stock = Integer.valueOf(request.getParameter(STOCK));
        String productType = request.getParameter(PRODUCT_TYPE);

        Product product = new Product(null, company, model, series, price, stock, productType);
        JDBCProductDAO jdbcProductDAO = JDBCProductDAO.getInstance();
        jdbcProductDAO.addNew(product);

        log.info(product.getProductType() + " " + product.getModel() + " added");

        return "/pages/home.jsp";
    }
}
