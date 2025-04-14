package org.example.managers.commands;

import org.example.system.Request;
import org.example.system.Receiver;

public class ExitCommand extends BaseCommand {

    public ExitCommand() {
        super("exit");
    }

//    @Override
//    public void execute(String[] args) {
//        System.exit(0);
//    }

    @Override
    public String execute(Request request) {
        return Receiver.exit(request);
    }

    @Override
    public String getDescription() {
        return "ends the program without saving it to a file";
    }
}

