package com.eshop.command;

import com.eshop.dao.entities.User;
import com.eshop.dao.jdbc.JDBCUserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String emailToBlock = request.getParameter("blockUser");
        if (emailToBlock != null) {

            JDBCUserDAO jdbc = JDBCUserDAO.getInstance();
            User user = jdbc.findEntity(emailToBlock);
            user.setActive(false);
            jdbc.update(user);
            page = "/pages/admin.jsp";

        } else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user != null && user.isAdmin()) {
                List<User> userList; //if null create

                userList = JDBCUserDAO.getInstance().findAll();
                session.setAttribute("userList", userList);
                page = "/pages/admin.jsp";

            } else {
                page = "/pages/404.jsp";
            }
        }
        return page;
    }
}
