package com.eshop.command;

import com.eshop.dao.entities.Product;
import com.eshop.dao.jdbc.JDBCProductDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProductCommand implements ICommand {

    private static final Logger log = Logger.getLogger(DeleteProductCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JDBCProductDAO jdbcProductDAO = JDBCProductDAO.getInstance();
        String productSeries = request.getParameter("delete");
        Product product = jdbcProductDAO.findEntity(productSeries);
        jdbcProductDAO.delete(productSeries);
        jdbcProductDAO.update(product);

        log.info(product.getModel() + " " + product.getProductType() + " deleted");
        return "/pages/home.jsp";
    }
}
