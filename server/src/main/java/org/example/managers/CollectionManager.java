package org.example.managers;

import org.example.recources.Dragon;
import org.example.system.Request;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;

public class CollectionManager {
    public static Hashtable<Long, Dragon> hashTable = new Hashtable<>();
    private static LocalDate date;


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
}
