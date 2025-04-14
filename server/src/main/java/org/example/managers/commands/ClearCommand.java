package org.example.managers.commands;

import org.example.system.Request;
import org.example.system.Receiver;

public class ClearCommand extends BaseCommand {
    public ClearCommand() {
        super("clear");
    }

//    @Override
//    public void execute(String[] args) {
//        ServerEnvironment.getInstance().getCollectionManager().getCollection().clear();
//        System.out.println("Collection is cleared.");
//    }

    @Override
    public String execute(Request request) {
        return Receiver.clear(request);
    }

    @Override
    public String getDescription() {
        return "clears the collection";
    }
}

