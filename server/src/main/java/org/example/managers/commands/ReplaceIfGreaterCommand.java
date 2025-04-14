package org.example.managers.commands;

import org.example.system.Request;
import org.example.system.Receiver;

public class ReplaceIfGreaterCommand extends BaseCommand{
    public ReplaceIfGreaterCommand(){
        super("replace_if_greater");
    }

//    @Override
//    public void execute(String[] args) {
//        try {
//            if (args.length == 0) {
//                throw new IllegalArgumentException("Error: No argument provided. Please specify a weight value.");
//            }
//            String key = args[0];
//            CollectionManager manager = Environment.getInstance().getCollectionManager();
//            if (manager.getCollection().contains(key)) {
//                DragonGenerator dragonGenerator = Environment.getInstance().getDragonGenerator();
//                Dragon dragon = new Dragon();
//                dragon.setAge(dragonGenerator.readAge());
//                if (manager.getCollection().get(key).compareTo(dragon) == 1) {
//                    dragon = dragonGenerator.createDragon();
//                    manager.getCollection().remove(key);
//                    manager.add(dragon);
//                } else {
//                    System.out.println("New dragon's age is less. Try again!");
//                }
//            }
//        } catch (NumberFormatException e) {
//            System.out.println("Error: Invalid argument. Please enter a valid integer.");
//        } catch (IllegalArgumentException e) {
//            System.err.println(e.getMessage());
//        }
//    }

    @Override
    public String execute(Request request) {
        return Receiver.replaceIfGreater(request);
    }

    @Override
    public String getDescription() {
        return "updates element by key if new bigger than element in collection with the same key";
    }
}
