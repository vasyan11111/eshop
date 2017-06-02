package com.eshop.commands;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandHome implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/pages/home.jsp";
    }
}
