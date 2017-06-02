package com.eshop.commands;

import com.eshop.dao.entities.User;
import com.eshop.dao.jdbc.JDBCUserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandRegistration implements ICommand {

    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phoneNumber";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;

        String login = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String phoneNumber = request.getParameter(PHONE_NUMBER);

        User user = new User(null, firstName, lastName, password, 2, phoneNumber, login, true);
        JDBCUserDao jdbcUserDao = null;
        try {
            jdbcUserDao = JDBCUserDao.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        jdbcUserDao.addNew(user);
        if(jdbcUserDao.find(login) != null){
            page = "/pages/success.jsp";
        }
        else {
            page = "/pages/404.jsp";
        }

        return page;
    }
}
