package org.example.managers.commands;

import org.example.system.Request;
import org.example.system.Receiver;

public class ShowCommand extends BaseCommand {
    public ShowCommand() {
        super("show");
    }

//    @Override
//    public void execute(String[] args) {
//        Hashtable<Long, Dragon> dragons = Environment.getInstance().getCollectionManager().getCollection();
//        dragons.forEach((k, v) -> System.out.println(v.toString()));
//    }

    @Override
    public String execute(Request request) {
        return Receiver.show(request);
    }

    @Override
    public String getDescription() {
        return "shows the elements of a collection.";
    }
}
