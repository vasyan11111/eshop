package com.eshop.commands;

import com.eshop.dao.entities.Product;
import com.eshop.dao.jdbc.JDBCMobilesDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CommandPhones implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBCMobilesDAO mobilesDAO = JDBCMobilesDAO.getInstance();
        List<Product> phones = mobilesDAO.getAll();
        request.setAttribute("phones", phones);
        return "/pages/phones.jsp";
    }
}
