package com.eshop.command;

import com.eshop.dao.entities.Product;
import com.eshop.dao.jdbc.JDBCProductDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProductCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JDBCProductDAO jdbcProductDAO = JDBCProductDAO.getInstance();
        String productSeries = request.getParameter("deleteFromPhones");
        Product product = jdbcProductDAO.findEntity(productSeries);
        jdbcProductDAO.delete(productSeries);
        jdbcProductDAO.update(product);

        return "/pages/phones.jsp";
    }
}
