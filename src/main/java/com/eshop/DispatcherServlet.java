package com.eshop;

import com.eshop.controller.*;

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

        String requestedURI = req.getRequestURI().replaceAll("/","");

        if (requestedURI.equals("eshoplogin")) {
            new LoginController().doAction(req, resp);

        } else if (requestedURI.equals("eshop")) {
            new HomeController().doAction(req, resp);

        } else if (requestedURI.equals("eshopaccount")) {
            AccountController.getInstance().doAction(req, resp);

        } else if (requestedURI.equals("eshoplaptops")) {
            new LaptopAssortmentController().doAction(req, resp);

        } else if (requestedURI.equals("eshopmobiles")) {
            new MobileAssortmentController().doAction(req, resp);

        } else if (requestedURI.equals("eshopsuccess")) {
            new SuccessController().doAction(req, resp);

        } else if (requestedURI.equals("eshopbucket")) {
            new HomeController().doAction(req, resp);

        } else {
            new ErrorController().doAction(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO:
    }
}
