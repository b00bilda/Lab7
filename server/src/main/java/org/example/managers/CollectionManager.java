package org.example.managers;

import java.time.LocalDate;
import java.util.Hashtable;

public class CollectionManager {
    public Hashtable<Long, Dragon> hashTable;
    private static LocalDate date;


    public CollectionManager() {
        new IdGenerator();
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
}
