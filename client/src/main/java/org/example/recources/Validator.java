package org.example.recources;

// валидация вводимых данных на клиенте
public class Validator {
    public static void validateId(String arg) {
        long id;
        try {
            id = Long.parseLong(arg);
        } catch (IllegalArgumentException e) {
            System.out.println("ID");
        }
    }

    public static void inputIsNotEmpty(String arg) {
        if (arg.isEmpty() || arg.trim().isEmpty()) {
            throw new IllegalArgumentException("");
        }
    }

    public static void validateCoordinateX(String arg) {
        try {
            double x = Double.parseDouble(arg);
            if (x > 909) {
                throw new IllegalArgumentException("X");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("X");
        }
    }

    public static void validateCoordinateY(String arg) {
        try {
            long y = Long.parseLong(arg);
        } catch (IllegalArgumentException e) {
            System.out.println("Y");
        }
    }

    public static void validateAge(String arg) {
        try {
            long age = Long.parseLong(arg);
            if (age <= 0) {
                throw new IllegalArgumentException("age");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("age");
        }
    }

    public static void validateWeight(String arg) {
        try {
            int weight = Integer.parseInt(arg);
            if (weight <= 0) {
                throw new IllegalArgumentException("weight");
            }
        } catch (IllegalArgumentException e) {
            System.out.println();
        }
    }

}
