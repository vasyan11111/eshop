package com.eshop.controller;


import com.eshop.command.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class ControllerHelper {

    private static ControllerHelper instance = null;
    private HashMap<String, ICommand> commands = new HashMap<>();

    private ControllerHelper(){
        commands.put("login", new LoginCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("phones", new PhonesCommand());
        commands.put("laptops", new LaptopsCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("bucket", new CartCommand());
        commands.put("admin", new AdminCommand());
        commands.put("delete", new DeleteProductCommand());
        commands.put("order", new OrderCommand());
        commands.put("addProduct", new AddNewProductCommand());
        commands.put("addMoney", new AddMoneyCommand());
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = commands.get(request.getParameter("command"));
        if (command == null) {
            command = new HomeCommand();
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
