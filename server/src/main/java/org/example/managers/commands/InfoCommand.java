package org.example.managers.commands;

import org.example.managers.CollectionManager;
import org.example.recources.Dragon;

public class InfoCommand extends BaseCommand {
    public InfoCommand() {
        super("info");
    }

    @Override
    public void execute(String[] args) {
        CollectionManager manager = Environment.getInstance().getCollectionManager();
        System.out.println("collection type: " + manager.getClass().getSimpleName());
        System.out.println("element type: " + Dragon.class.getSimpleName());
        System.out.println("initialization date: " + manager.getInitializationDate());
        System.out.println("amount of elements: " + manager.getCollection().size());
    }

    @Override
    public String getDescription() {
        return "show information about collection";
    }

}
