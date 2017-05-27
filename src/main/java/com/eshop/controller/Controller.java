package com.eshop.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {

    void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
