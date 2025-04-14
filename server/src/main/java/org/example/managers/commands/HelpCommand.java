package org.example.managers.commands;

import org.example.system.Request;
import org.example.system.Receiver;

public class HelpCommand extends BaseCommand {

    public HelpCommand() {
        super("help");
    }

//    @Override
//    public void execute(String[] args) {
//        ServerEnvironment.getInstance().getCommandManager().getCommandList().forEach((s, command) -> {
//            System.out.println(s + ": " + command.getDescription());
//        });
//    }

    @Override
    public String execute(Request request) {
        return Receiver.help(request);
    }

    @Override
    public String getDescription() {
        return "show information about available commands";
    }
}

