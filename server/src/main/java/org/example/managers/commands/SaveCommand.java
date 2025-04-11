package org.example.managers.commands;

public class SaveCommand extends BaseCommand {
    public SaveCommand() {
        super("save");
    }

    @Override
    public void execute(String[] args) {
        Environment.getInstance().getFileManager().saveToFile();
    }

    @Override
    public String getDescription() {
        return "";
    }
}
