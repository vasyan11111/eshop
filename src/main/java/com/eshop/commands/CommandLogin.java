package com.eshop.commands;


import com.eshop.dao.entities.User;
import com.eshop.dao.jdbc.JDBCUserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CommandLogin implements ICommand {

    private static final String LOGIN = "email";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String page;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        User user = null;
        try {
            user = JDBCUserDAO.getInstance().find(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user != null && user.getPassword().equals(password)){
            page = "/pages/success.jsp";
        }
        else {
            page = "/pages/404.jsp";
        }
        return page;
    }
}
