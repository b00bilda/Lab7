package org.example.managers.commands;

import org.example.system.Request;
import org.example.system.Receiver;

public class RemoveLowerCommand extends BaseCommand {
    public RemoveLowerCommand() {
        super("remove_lower");
    }

//    @Override
//    public void execute(String[] args) {
//        try {
//            if (args.length == 0) {
//                throw new IllegalArgumentException("Error: No argument provided. Please specify a weight value.");
//            }
//            int minWeight = Integer.parseInt(args[0]);
//            CollectionManager manager = Environment.getInstance().getCollectionManager();
//            Iterator<Map.Entry<Long, Dragon>> iterator = manager.getCollection().entrySet().iterator();
//            while (iterator.hasNext()) {
//                Map.Entry<Long, Dragon> entry = iterator.next();
//                if (entry.getValue().getWeight() < minWeight) {
//                    iterator.remove();
//                }
//            }
//            System.out.println("New collection: ");
//            manager.getCollection().forEach((id, dragon) -> System.out.println(dragon.toString()));
//        } catch (NumberFormatException e) {
//            System.out.println("Error: Invalid argument. Please enter a valid integer.");
//        } catch (IllegalArgumentException e) {
//            System.err.println(e.getMessage());
//        }
//    }

    @Override
    public String execute(Request request) {
        return Receiver.removeLower(request);
    }

    @Override
    public String getDescription() {
        return "this command removes all items smaller than the specified one from the collection.";
    }
}
