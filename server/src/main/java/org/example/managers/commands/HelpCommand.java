package org.example.managers.commands;

import org.example.managers.ServerEnvironment;

public class HelpCommand extends BaseCommand {

    public HelpCommand() {
        super("help");
    }

    @Override
    public void execute(String[] args) {
        ServerEnvironment.getInstance().getCommandManager().getCommandList().forEach((s, command) -> {
            System.out.println(s + ": " + command.getDescription());
        });
    }

    @Override
    public String getDescription() {
        return "show information about available commands";
    }
}

