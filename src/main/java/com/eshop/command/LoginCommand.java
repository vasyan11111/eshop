package com.eshop.command;


import com.eshop.dao.entities.User;
import com.eshop.dao.jdbc.JDBCUserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginCommand implements ICommand {

    private static final String LOGIN = "email";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String page;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        User user = null;
        try {
            user = JDBCUserDAO.getInstance().findEntity(login);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (user != null && user.getPassword().equals(password)){
            page = "/pages/home.jsp";
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        }
        else {
            page = "/pages/404.jsp";
        }
        return page;
    }
}
