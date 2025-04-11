package org.example.managers.commands;

import org.example.recources.Dragon;

import java.util.Hashtable;

public class ShowCommand extends BaseCommand {
    public ShowCommand() {
        super("show");
    }

    @Override
    public void execute(String[] args) {
        Hashtable<Long, Dragon> dragons = Environment.getInstance().getCollectionManager().getCollection();
        dragons.forEach((k, v) -> System.out.println(v.toString()));
    }

    @Override
    public String getDescription() {
        return "shows the elements of a collection.";
    }
}
