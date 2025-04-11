package org.example.managers.commands;

import org.example.recources.Dragon;

public class InsertCommand extends BaseCommand {
    public InsertCommand(){
        super("insert");
    }

    @Override
    public void execute(String[] args) {
        DragonGenerator dragonGenerator = Environment.getInstance().getDragonGenerator();
        Object dragon = dragonGenerator.createDragon();
        Environment.getInstance().getCollectionManager().add((Dragon) dragon);
        System.out.println("Element was added to collection");
    }

    @Override
    public String getDescription() {
        return "this command adds a new element with the given key.";
    }
}
