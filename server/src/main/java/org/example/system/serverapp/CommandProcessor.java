package org.example.system.serverapp;

import org.example.managers.ServerEnvironment;
import org.example.managers.commands.BaseCommand;

class CommandProcessor {

    public String process(String commandName, String[] args) {
        BaseCommand command = ServerEnvironment.getInstance().getCommandManager().getCommandList().get(commandName);
        if (command != null) {
            return command.execute(args);
        } else {
            return "Unknown command: " + commandName;
        }
    }

    interface Command {
        String execute(String[] args);
    }
}
