package org.example.system.serverapp;

class CommandProcessor {

    public String process(String commandName, String[] args) {
        Command command = commandMap.get(commandName);
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
