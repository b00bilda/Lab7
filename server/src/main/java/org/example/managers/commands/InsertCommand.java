package org.example.managers.commands;

import org.example.system.Request;
import org.example.system.Receiver;

public class InsertCommand extends BaseCommand {
    public InsertCommand() {
        super("insert");
    }


    @Override
    public String execute(Request request) {
        return Receiver.insert(request);
    }


//    @Override
//    public void execute(String[] args) {
//        DragonGenerator dragonGenerator = Environment.getInstance().getDragonGenerator();
//        Object dragon = dragonGenerator.createDragon();
//        Environment.getInstance().getCollectionManager().add((Dragon) dragon);
//        System.out.println("Element was added to collection");
//    }


    public String getDescription() {
        return "this command adds a new element with the given key.";
    }
}
