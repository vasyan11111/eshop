package com.eshop.command;

import com.eshop.dao.entities.User;
import com.eshop.dao.jdbc.JDBCUserDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddMoneyCommand implements ICommand {

    private static final Logger log = Logger.getLogger(AddMoneyCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long amount = Long.valueOf(request.getParameter("amount"));

        if (amount == null && amount > Integer.MAX_VALUE){
            return "/pages/error.jsp";
        }
        Integer cashAmount = Integer.valueOf(request.getParameter("amount"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        user = JDBCUserDAO.getInstance().addCash(user.getEmail(), cashAmount);
        session.setAttribute("user", user);
        log.info(user.getEmail() + " added " + cashAmount + " money on account");
        return "/pages/home.jsp";
    }
}
