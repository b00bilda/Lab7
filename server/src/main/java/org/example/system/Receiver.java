package org.example.system;

import org.example.managers.CollectionManager;
import org.example.managers.ServerEnvironment;
import org.example.recources.Dragon;

public class Receiver {
    public static String clear(Request request) {
        if (request.getMessage().split(" ").length == 1) {
            ServerEnvironment.getInstance().getCollectionManager().getCollection().clear();
            return "Collection is cleared";
        } else {
            throw new IllegalArgumentException("command parameter");
        }
    }

    public static String exit(Request request) {
        if (request.getMessage().split("").length == 1) {
            System.exit(0);
            return "";
        } else {
            throw new IllegalArgumentException("command parameter");
        }
    }

    public static String filterLessThanWeight(Request request) {
        if (request.getMessage().split(" ").length == 2) {
            ServerEnvironment.getInstance().getCollectionManager().filterLessThanWeight(request);
            return "Collection was changed";
        } else {
            throw new IllegalArgumentException("command parameter");
        }
    }

    public static String help(Request request) {
        if (request.getMessage().split(" ").length == 1) {
            StringBuilder text = new StringBuilder();
            ServerEnvironment.getInstance().getCommandManager().getCommandList().forEach((s, command) -> {
                text.append(s + ": " + command.getDescription() + "\n");
            });
            return text.toString();
        } else {
            throw new IllegalArgumentException("command parameter");
        }
    }

    public static String info(Request request) {
        if (request.getMessage().split(" ").length == 1) {
            CollectionManager manager = ServerEnvironment.getInstance().getCollectionManager();
//            System.out.println("collection type: " + manager.getClass().getSimpleName());
//            System.out.println("element type: " + Dragon.class.getSimpleName());
//            System.out.println("initialization date: " + manager.getInitializationDate());
//            System.out.println("amount of elements: " + manager.getCollection().size());
            return "collection type: " + manager.getClass().getSimpleName() + "\n" +
                    "element type: " + Dragon.class.getSimpleName() + "\n" +
                    "initialization date: " + manager.getInitializationDate() + "\n" +
                    "amount of elements: " + manager.getCollection().size();
        } else {
            throw new IllegalArgumentException("command parameter");
        }
    }

    public static String insert(Request request) {
        CollectionManager manager = ServerEnvironment.getInstance().getCollectionManager();
        if (request.getMessage().split(" ").length == 1) {
            manager.add(request.getDragon());
            return "Element was added";
        } else {
            throw new IllegalArgumentException("command parameter");
        }
    }

    public static String showMinByCoordinates(Request request) {
        return ServerEnvironment.getInstance().getCollectionManager().getMinByCoordinates(request);
    }

    public static String remove(Request request) {
        long key = Long.parseLong(request.getMessage().split(" ")[1]);
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
        if (request.getMessage().split(" ").length == 1) {
            ServerEnvironment.getInstance().getFileManager().saveToFile();
            return "Data was saved";
        } else {
            throw new IllegalArgumentException("command parameter");
        }
    }

    public static String sumOfAge(Request request) {
        if (request.getMessage().split(" ").length == 1) {
            return ServerEnvironment.getInstance().getCollectionManager().getSumOfAges();
        } else {
            throw new IllegalArgumentException("command parameter");
        }
    }

    public static String updateById(Request request) {
        CollectionManager manager = ServerEnvironment.getInstance().getCollectionManager();
        long key = Long.parseLong(request.getMessage().split(" ")[1]);
        if (request.getMessage().split(" ").length == 2 & manager.getCollection().containsKey(key)) {
            manager.getCollection().remove(key);
            manager.add(request.getDragon());
            return "Element was updated";
        } else {
            throw new IllegalArgumentException("command parameter");
        }
    }

    public static String show(Request request) {
        if (request.getMessage().split(" ").length == 1) {
            return ServerEnvironment.getInstance().getCollectionManager().show(request);
        } else {
            throw new IllegalArgumentException("command parameter");
        }
    }
}



