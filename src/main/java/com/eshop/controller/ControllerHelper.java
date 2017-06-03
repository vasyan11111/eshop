package com.eshop.controller;


import com.eshop.commands.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class ControllerHelper {

    private static ControllerHelper instance = null;
    private HashMap<String, ICommand> commands = new HashMap<>();

    private ControllerHelper(){
        commands.put("login", new CommandLogin());
        commands.put("registration", new CommandRegistration());
        commands.put("phones", new CommandPhones());
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
