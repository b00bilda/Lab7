package org.example.system;

import org.example.managers.CollectionManager;
import org.example.managers.ServerEnvironment;
import org.example.recources.Dragon;

public class Receiver {
    public static String clear(Request request) {
        ServerEnvironment.getInstance().getCollectionManager().getCollection().clear();
        return "Collection is cleared";
    }

    public static String exit(Request request) {
        System.exit(0);
        return "";
    }

    public static String filterLessThanWeight(Request request) {
        if (request.getArgs().length == 1) {
            ServerEnvironment.getInstance().getCollectionManager().filterLessThanWeight(request);
            return "Collection was changed";
        } else {
            throw new IllegalArgumentException("command parameter");
        }
    }

    public static String help(Request request) {
        StringBuilder text = new StringBuilder();
        ServerEnvironment.getInstance().getCommandManager().getCommandList().forEach((s, command) -> {
            text.append(s + ": " + command.getDescription() + "\n");
        });
        return text.toString();
    }

    public static String info(Request request) {
        CollectionManager manager = ServerEnvironment.getInstance().getCollectionManager();
        return "collection type: " + manager.getClass().getSimpleName() + "\n" +
                "element type: " + Dragon.class.getSimpleName() + "\n" +
                "initialization date: " + manager.getInitializationDate() + "\n" +
                "amount of elements: " + manager.getCollection().size();
    }

    public static String insert(Request request) {
        CollectionManager manager = ServerEnvironment.getInstance().getCollectionManager();
        manager.add(request.getDragon());
        return "Element was added";
    }

    public static String showMinByCoordinates(Request request) {
        return ServerEnvironment.getInstance().getCollectionManager().getMinByCoordinates(request);
    }

    public static String remove(Request request) {
        long key = Long.parseLong(request.getArgs()[1]);
        ServerEnvironment.getInstance().getCollectionManager().getCollection().remove(key);
        return "Element was removed";
    }

    public static String removeGreater(Request request) {
        return ServerEnvironment.getInstance().getCollectionManager().removeGreater(request.getDragon());
    }

    public static String removeLower(Request request) {
        return ServerEnvironment.getInstance().getCollectionManager().removeLower(request.getDragon());
    }

    public static String replaceIfGreater(Request request) {
        return ServerEnvironment.getInstance().getCollectionManager().replaceIfGreater(request);
    }

    public static String save(Request request) {
        ServerEnvironment.getInstance().getFileManager().saveToFile();
        return "Data was saved";
    }

    public static String sumOfAge(Request request) {
        return ServerEnvironment.getInstance().getCollectionManager().getSumOfAges();
    }

    public static String updateById(Request request) {
        CollectionManager manager = ServerEnvironment.getInstance().getCollectionManager();
        long key = Long.parseLong(request.getArgs()[1]);
        if (request.getArgs().length == 1 & manager.getCollection().containsKey(key)) {
            manager.getCollection().remove(key);
            manager.add(request.getDragon());
            return "Element was updated";
        } else {
            throw new IllegalArgumentException("command parameter");
        }
    }

    public static String show(Request request) {
        return ServerEnvironment.getInstance().getCollectionManager().show(request);
    }
}



