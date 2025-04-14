package org.example.managers.commands;

import org.example.system.Request;
import org.example.system.Receiver;

public class InfoCommand extends BaseCommand {
    public InfoCommand() {
        super("info");
    }

//    @Override
//    public void execute(String[] args) {
//        CollectionManager manager = ServerEnvironment.getInstance().getCollectionManager();
//        System.out.println("collection type: " + manager.getClass().getSimpleName());
//        System.out.println("element type: " + Dragon.class.getSimpleName());
//        System.out.println("initialization date: " + manager.getInitializationDate());
//        System.out.println("amount of elements: " + manager.getCollection().size());
//    }

    @Override
    public String execute(Request request) {
        return Receiver.info(request);
    }

    @Override
    public String getDescription() {
        return "show information about collection";
    }

}
