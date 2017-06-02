package com.eshop.controller;


import com.eshop.commands.CommandHome;
import com.eshop.commands.CommandLogin;
import com.eshop.commands.CommandRegistration;
import com.eshop.commands.ICommand;
import com.eshop.service.DataSource;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class ControllerHelper {

    private static ControllerHelper instance = null;
    private HashMap<String, ICommand> commands = new HashMap<>();

    private ControllerHelper(){
        commands.put("login", new CommandLogin());
        commands.put("registration", new CommandRegistration());
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = commands.get(request.getParameter("command"));
        if (command == null) {
            command = new CommandHome();
        }
        return command;
    }

    public static ControllerHelper getInstance() throws Exception {
        if (instance == null) {
            synchronized (ControllerHelper.class){
                if (instance == null)
                    instance = new ControllerHelper();
            }
        }
        return instance;
    }
}
