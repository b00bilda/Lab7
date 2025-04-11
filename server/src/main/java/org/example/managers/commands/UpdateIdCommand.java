package org.example.managers.commands;

import org.example.managers.CollectionManager;
import org.example.recources.Dragon;

public class UpdateIdCommand extends BaseCommand {
    public UpdateIdCommand() {
        super("update_id");
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length == 0) {
                throw new IllegalArgumentException("Error: No argument provided. Please specify a weight value.");
            }
            long id = Long.parseLong(args[0]);
            boolean isInCollection = false;
            CollectionManager manager = Environment.getInstance().getCollectionManager();
            for (Long key : manager.getCollection().keySet()) {
                if (manager.getCollection().get(key).getID() == id) {
                    isInCollection = true;
                    System.out.println("Updating element with id:" + id);
                    DragonGenerator dragonGenerator = Environment.getInstance().getDragonGenerator();
                    Dragon newDragon = dragonGenerator.createDragon(id);

                    manager.getCollection().remove(key);
                    manager.getCollection().put(key, newDragon);
                    System.out.println("Element with id: " + id + "was updated.");
                }
            }
            if (!isInCollection) {
                System.out.println("Element with this id doesn't exist, try again!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid argument. Please enter a valid integer.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "this command updates the value of a collection item using id.";
    }
}
