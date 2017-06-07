package com.eshop.command;

import com.eshop.dao.entities.User;
import com.eshop.dao.jdbc.JDBCUserDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements ICommand {

    private static final Logger log = Logger.getLogger(RegistrationCommand.class);


    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final Double DEFAULT_CASH = 0d;
    private static final Boolean ACTIVE = true;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;


        String login = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String phoneNumber = request.getParameter(PHONE_NUMBER);

        User user = User.newBuilder()
                .setEmail(login)
                .setPassword(password)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhoneNumber(phoneNumber)
                .setCash(DEFAULT_CASH)
                .setActive(ACTIVE)
                .build();


        JDBCUserDAO jdbcUserDAO = JDBCUserDAO.getInstance();

        if (jdbcUserDAO.addNew(user)) {
            page = "/pages/home.jsp";
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            log.info(user.getEmail() + " registered");
        } else {
            page = "/pages/404.jsp";
        }

        return page;
    }
}
