package org.example.managers.commands;

import org.example.managers.ServerEnvironment;

public class ClearCommand extends BaseCommand {
    public ClearCommand() {
        super("clear");
    }

    @Override
    public void execute(String[] args) {
        ServerEnvironment.getInstance().getCollectionManager().getCollection().clear();
        System.out.println("Collection is cleared.");
    }

    @Override
    public String getDescription() {
        return "clears the collection";
    }
}

