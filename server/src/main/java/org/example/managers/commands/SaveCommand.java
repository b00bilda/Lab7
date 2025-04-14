package org.example.managers.commands;

import org.example.system.Request;
import org.example.system.Receiver;

public class SaveCommand extends BaseCommand {
    public SaveCommand() {
        super("save");
    }

//    @Override
//    public void execute(String[] args) {
//        Environment.getInstance().getFileManager().saveToFile();
//    }

    @Override
    public String execute(Request request) {
        return Receiver.save(request);
    }

    @Override
    public String getDescription() {
        return "downloads collection to file";
    }
}
