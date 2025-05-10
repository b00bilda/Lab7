package org.example.managers;

import org.example.recources.Dragon;
import org.example.system.Request;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.example.system.Receiver.downloadData;

public class CollectionManager {
    public static Hashtable<Long, Dragon> hashTable = new Hashtable<>();
    private static LocalDate date;
    private static long sumOfAges;


    public CollectionManager() {
        // new IdGenerator();
        hashTable = new Hashtable<>();
    }

    public Hashtable<Long, Dragon> getCollection() {
        return hashTable;
    }

    // Добавление нового дракона
    public void add(Dragon dragon) {
        if (hashTable == null) {
            hashTable = new Hashtable<>();
        }
        hashTable.put(dragon.getID(), dragon);
    }

    public static LocalDate getInitializationDate() {
        return date;
    }

    public void setTable(Hashtable<Long, Dragon> table) {
        CollectionManager.hashTable = table;
    }

    public String filterLessThanWeight(Request request) {
        if (hashTable.isEmpty()) {
            return "Collection in empty";
        }
        int weight = Integer.parseInt(request.getArgs()[0]);
        StringBuilder text = new StringBuilder();
        ServerEnvironment.getInstance().getCollectionManager().getCollection().forEach((key, dragon) -> {
            int comparisonWeight = weight;
            if (dragon.getWeight() < comparisonWeight) {
                text.append(dragon.toString());
            }
        });
        return text.toString();
    }

    public String getMinByCoordinates(Request request) {
        if (hashTable.isEmpty()) {
            return "Collection is empty";
        } else {
            CollectionManager manager = ServerEnvironment.getInstance().getCollectionManager();
            Comparator<Dragon> dragonComparator = Comparator
                    .comparing((Dragon d) -> d.getCoordinates().getX())
                    .thenComparing(d -> d.getCoordinates().getY());
            Dragon min = Collections.min(manager.getCollection().values(), dragonComparator);
            return min.toString();
        }
    }

    public String removeGreater(Dragon dragon) {
        if (hashTable.isEmpty()) {
            return "Collection is empty";
        } else {
            int maxWeight = dragon.getWeight();
            CollectionManager manager = ServerEnvironment.getInstance().getCollectionManager();
            Iterator<Map.Entry<Long, Dragon>> iterator = manager.getCollection().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Long, Dragon> entry = iterator.next();
                if (entry.getValue().getWeight() > maxWeight) {
                    if (DatabaseManager.removeOrganizationById(entry.getValue().getID())) {
                        downloadData();
                        return "Collection was changed";
                    } else {
                        return "Collection wasn't changed";
                    }
                } else {
                    return "Collection wasn't changed";
                }
            }
            return "";
        }
    }

    public String removeLower(Dragon dragon) {
        if (hashTable.isEmpty()) {
            return "Collection is empty";
        } else {
            int minWeight = dragon.getWeight();
            CollectionManager manager = ServerEnvironment.getInstance().getCollectionManager();
            Iterator<Map.Entry<Long, Dragon>> iterator = manager.getCollection().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Long, Dragon> entry = iterator.next();
                if (entry.getValue().getWeight() < minWeight) {
                    if (DatabaseManager.removeOrganizationById(entry.getValue().getID())) {
                        downloadData();
                        return "Collection was changed";
                    } else {
                        return "Collection wasn't changed";
                    }
                }
            }
            return "";
        }
    }

    public String replaceIfGreater(Request request) {
        if (hashTable.isEmpty()) {
            return "Collection is empty";
        }
        String key = request.getMessage().split(" ")[1];
        CollectionManager manager = ServerEnvironment.getInstance().getCollectionManager();
        if (manager.getCollection().contains(key)) {
            // DragonGenerator dragonGenerator = Environment.getInstance().getDragonGenerator();
            Dragon dragon = request.getDragon();
            if (manager.getCollection().get(key).compareTo(dragon) == 1) {
                if (DatabaseManager.updateDragonById(manager.getCollection().get(key).getID(), request.getUsername(), dragon)) {
                    downloadData();
                    return "Changes was accepted";
                }
            } else {
                System.out.println("New dragon's age is less. Try again!");
            }
        }
        return "";
    }

    public String getSumOfAges() {
        hashTable.forEach((s, dragon) -> {
            sumOfAges = sumOfAges + dragon.getAge();
        });
        return Long.toString(sumOfAges);
    }

    public String show(Request request) {
        if (hashTable.isEmpty()) {
            return "Collection is empty";
        }
        StringBuilder text = new StringBuilder();
        if (request.getMessage().split(" ").length == 1) {
            text.append(hashTable.entrySet().stream()
                    .map(entry -> entry.getKey() + ": " + entry.getValue())
                    .collect(Collectors.joining("\n")));
        }
        return text.toString();
    }

}
