package org.example.managers.commands;
import org.example.managers.ServerEnvironment;
import org.example.system.Request;

public class ReadCommand extends BaseCommand {
    public ReadCommand() {
        super("read");
    }


    @Override
    public String execute(Request request) {
        return "";
    }

    @Override
    public String getDescription() {
        return "reads data about collection from file";
    }
}
