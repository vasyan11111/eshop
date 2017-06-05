package com.eshop.command;

import com.eshop.dao.entities.Order;
import com.eshop.dao.entities.User;
import com.eshop.dao.jdbc.JDBCOrderDAO;
import com.eshop.dao.jdbc.JDBCUserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OrderCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JDBCOrderDAO orderDAO = getOrderDao();

        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        Order cart = (Order) session.getAttribute("cart");

        if (cart.getUser().getId() == null) {
            JDBCUserDAO userDAO = JDBCUserDAO.getInstance();
            User user = userDAO.findEntity(cart.getUser().getEmail());
            cart.setUser(user);
        }

        boolean result = orderDAO.addNew(cart);
        if (result) {
            session.removeAttribute("cart");
        } else {
            return "/pages/error.jsp";
        }


        return "/pages/confirmationPage.jsp";
    }

    private JDBCOrderDAO getOrderDao() {
        return JDBCOrderDAO.getInstance();
    }
}
