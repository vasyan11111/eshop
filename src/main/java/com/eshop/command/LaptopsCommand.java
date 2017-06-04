package com.eshop.command;

import com.eshop.dao.entities.Product;
import com.eshop.dao.jdbc.JDBCProductDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LaptopsCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBCProductDAO jdbcProductDAO = JDBCProductDAO.getInstance();
        List<Product> laptops = jdbcProductDAO.findSpecifiedProduct("'Laptop'");
        request.setAttribute("laptops", laptops);
        return "/pages/laptops.jsp";
    }
}
