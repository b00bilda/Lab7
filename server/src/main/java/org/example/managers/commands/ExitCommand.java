package org.example.managers.commands;

public class ExitCommand extends BaseCommand {

    public ExitCommand() {
        super("exit");
    }

    @Override
    public void execute(String[] args) {
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "ends the program without saving it to a file";
    }
}

