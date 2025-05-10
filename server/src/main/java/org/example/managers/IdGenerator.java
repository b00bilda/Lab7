package org.example.managers;

public class IdGenerator {
    private static final int max = 1000000;

    public static Long generateId() {
        Long id = (long) (Math.random() * max + 1);
        return id;
    }
}
