package org.example.managers.commands;
import org.example.managers.ServerEnvironment;

public class ReadCommand extends BaseCommand {
    public ReadCommand() {
        super("read");
    }

    @Override
    public void execute(String[] args) {
        ServerEnvironment.getInstance().getFileManager().readFile();
        System.out.println("Collection was successfully read from file");
    }

    @Override
    public String getDescription() {
        return "reads data about collection from file";
    }
}
