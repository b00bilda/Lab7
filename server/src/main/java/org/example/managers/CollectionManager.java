package org.example.managers;

import org.example.recources.Dragon;
import org.example.system.Request;

import java.time.LocalDate;
import java.util.*;

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

    public String filterLessThanWeight(Request request) {
        if (hashTable.isEmpty()) {
            return "Collection in empty";
        }
        int weight = Integer.parseInt(request.getMessage().split(" ")[1]);
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
                    iterator.remove();
                }
            }
            return "Collection was changed";
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
                    iterator.remove();
                }
            }
            return "Collection was changed";
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
                manager.getCollection().remove(key);
                manager.add(dragon);
            } else {
                System.out.println("New dragon's age is less. Try again!");
            }
        }
        return "Changes was accepted";
    }

    public String getSumOfAges() {
        hashTable.forEach((s, dragon) -> {
            sumOfAges = sumOfAges + dragon.getAge();
        });
        return Long.toString(sumOfAges);
    }

}
