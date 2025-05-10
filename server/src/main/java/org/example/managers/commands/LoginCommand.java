package org.example.managers.commands;

import org.example.managers.DatabaseManager;
import org.example.system.Request;
import org.example.system.Server;

public class LoginCommand extends BaseCommand {
    public LoginCommand() {
        super("login");
    }

    @Override
    public String execute(Request request) {
        if (DatabaseManager.checkUser(request.getUsername(), request.getPassword())) {
            Server.addUser(request.getUsername());
            return "Welcome!";
        } else {
            return "Incorrect username or password";
        }
    }

    @Override
    public String getDescription() {
        return "this command login user";
    }
}
