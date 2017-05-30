package com.eshop.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountController implements Controller {

    private static volatile AccountController instance;

    private AccountController(){
    }

    public void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/pages/account.jsp");
        requestDispatcher.forward(req, resp);
    }

    public static AccountController getInstance(){
        if (instance == null){
            synchronized (AccountController.class){
                if (instance == null)
                    instance = new AccountController();
            }
        }
        return instance;
    }
}