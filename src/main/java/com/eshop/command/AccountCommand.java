package com.eshop.command;

import com.eshop.dao.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccountCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return "/pages/account.jsp";
    }
}


//SELECT *
//FROM Orders
//    JOIN Users On orders.userId = Users.id
//        WHERE users.email = 'irunnie@gmail.com';