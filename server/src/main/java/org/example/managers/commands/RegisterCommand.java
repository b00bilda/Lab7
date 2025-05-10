package org.example.managers.commands;

import org.example.managers.DatabaseManager;
import org.example.system.Receiver;
import org.example.system.Request;
import org.example.system.Server;

public class RegisterCommand extends BaseCommand {
    public RegisterCommand() {
        super("register");
    }

    @Override
    public String execute(Request request) {
        if (DatabaseManager.registrateUser(request.getArgs()[0], request.getArgs()[1])) {
            Server.addUser(request.getArgs()[0]);
            return "Registration is successful. Welcome!";
        } else {
            return "Something wrong with registration";
        }
    }

    @Override
    public String getDescription() {
        return "this command registers a new user";
    }
}
