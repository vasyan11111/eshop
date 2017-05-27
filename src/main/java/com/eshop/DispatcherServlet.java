package com.eshop;

import com.eshop.controller.LoginController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = null;

        if (req.getRequestURI().equals("/eshop/login")) {
            new LoginController().doAction(req, resp);
        } else if (req.getRequestURI().equals("/eshop/")) {
            requestDispatcher = req.getRequestDispatcher("/pages/home.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            requestDispatcher = req.getRequestDispatcher("/pages/404.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO:
    }
}
