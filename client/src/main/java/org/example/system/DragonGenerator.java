package org.example.system;

import org.example.recources.Coordinates;
import org.example.recources.Dragon;
import org.example.recources.DragonCave;
import org.example.recources.DragonType;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DragonGenerator {
    Scanner scanner = new Scanner(System.in);

    public Dragon createDragon() {
        String name;
        Coordinates coordinates;
        double coordinatesX;
        long coordinatesY;
        long age;
        int weight;
        Boolean speaking;
        DragonType type;
        DragonCave cave;
        Float caveDepth;
        int caveNumberOfTreasures;
        System.out.println("Welcome to dragon creator!");
        Long id = IdGenerator.generateId();
        System.out.println("Dragon's id: " + id);
        System.out.println("Write a String for dragon's name");
        name = readString();
        while (name == null || name.isEmpty()) {
            System.out.print("Dragon's name can't be empty, try again: ");
            name = readString();
        }
        System.out.println("Write a double for X coordinate");
        coordinatesX = readDouble();
        while (coordinatesX > 909) {
            System.out.println("X coordinate can't be higher than 909, try again: ");
            coordinatesX = readDouble();
        }
        System.out.println("Write a long for Y coordinates");
        coordinatesY = readLong();
        coordinates = new Coordinates(coordinatesX, coordinatesY);
        System.out.println("Write a long for dragon's age");
        age = readLong();
        while (age < 0) {
            System.out.println("Dragon's age can't be lower than 0, try again: ");
            age = readLong();
        }
        System.out.println("Write an int for dragon's weight");
        weight = readInt();
        while (weight < 0) {
            System.out.println("Dragon's weight can't be lower than 0, try again: ");
            weight = readInt();
        }
        System.out.println("Write a boolean for dragon's ability to speaking");
        speaking = readBoolean();
        while (speaking == null) {
            System.out.println("Dragon's ability to speaking can't be null, try again: ");
            speaking = readBoolean();
        }
        DragonType dragonType = null;
        while (dragonType == null) {
            System.out.print("Write a dragon's type (WATER, UNDERGROUND, AIR, FIRE)");
            scanner.nextLine();
            String value = readString().trim().toUpperCase();

            try {
                dragonType = DragonType.valueOf(value);
            } catch (IllegalArgumentException e) {
                System.out.println(value + " is not an available dragon's type, try again: ");
            }
        }
        type = dragonType;
        System.out.println("Write a Float for dragon cave's depth");
        caveDepth = readFloat();
        while (caveDepth == null) {
            System.out.println("Dragon cave's depth can't be null, try again: ");
            caveDepth = readFloat();
        }
        scanner.nextLine();
        System.out.println("Write an int for dragon cave's number of treasures");
        caveNumberOfTreasures = readInt();
        while (caveNumberOfTreasures < 0) {
            System.out.println("Dragon cave's number of treasures can't be lower than 0, try again: ");
            caveNumberOfTreasures = readInt();
        }
        cave = new DragonCave(caveNumberOfTreasures, caveDepth);
        return new Dragon(id, name, coordinates, age, weight, speaking, type, cave);
    }

    public Dragon createDragon(Long id) {
        String name;
        Coordinates coordinates;
        double coordinatesX;
        long coordinatesY;
        long age;
        int weight;
        Boolean speaking;
        DragonType type;
        DragonCave cave;
        Float caveDepth;
        int caveNumberOfTreasures;
        System.out.println("Welcome to dragon creator!");
        System.out.println("Write a String for dragon's name");
        name = scanner.nextLine();
        while (name == null || name.isEmpty()) {
            System.out.print("Dragon's name can't be empty, try again: ");
            name = scanner.nextLine();
        }
        System.out.println("Write a double for X coordinate");
        coordinatesX = readDouble();
        while (coordinatesX > 909) {
            System.out.println("X coordinate can't be higher than 909, try again: ");
            coordinatesX = readDouble();
        }
        System.out.println("Write a long for Y coordinates");
        coordinatesY = readLong();
        coordinates = new Coordinates(coordinatesX, coordinatesY);
        System.out.println("Write a long for dragon's age");
        age = readLong();
        while (age < 0) {
            System.out.println("Dragon's age can't be lower than 0, try again: ");
            age = readLong();
        }
        System.out.println("Write an int for dragon's weight");
        weight = readInt();
        while (weight < 0) {
            System.out.println("Dragon's weight can't be lower than 0, try again: ");
            weight = readInt();
        }
        System.out.println("Write a boolean for dragon's ability to speaking");
        speaking = readBoolean();
        while (speaking == null) {
            System.out.println("Dragon's ability to speaking can't be null, try again: ");
            speaking = readBoolean();
        }
//        System.out.println("Write a number for dragon's type (1 - WATER, 2 - UNDERGROUND, 3 - AIR, 4 - FIRE)");
//        int dragonTypeNumber;
        DragonType dragonType = null;
        while (dragonType == null) {
            System.out.print("Write a dragon's type (WATER, UNDERGROUND, AIR, FIRE)");
            scanner.nextLine();
            String value = readString().trim().toUpperCase();

            try {
                dragonType = DragonType.valueOf(value);
            } catch (IllegalArgumentException e) {
                System.out.println(value + " is not an available dragon's type, try again: ");
            }
        }
        type = dragonType;
        System.out.println("Write a Float for dragon cave's depth");
        caveDepth = readFloat();
        while (caveDepth == null) {
            System.out.println("Dragon cave's depth can't be null, try again: ");
            caveDepth = readFloat();
        }
        scanner.nextLine();
        System.out.println("Write an int for dragon cave's number of treasures");
        caveNumberOfTreasures = readInt();
        while (caveNumberOfTreasures < 0) {
            System.out.println("Dragon cave's number of treasures can't be lower than 0, try again: ");
            caveNumberOfTreasures = readInt();
        }
        cave = new DragonCave(caveNumberOfTreasures, caveDepth);
        return new Dragon(id, name, coordinates, age, weight, speaking, type, cave);
    }

    public String readName() {
        System.out.println("Write a String for dragon's name");
        String name = readString();
        while (name == null && name.isEmpty()) {
            System.out.print("Dragon's name can't be empty, try again: ");
            name = readString();
        }
        return name;
    }

    public Coordinates readCoordinates() {
        double x;
        long y;
        System.out.println("Write a double for X coordinate");
        x = readDouble();
        while (x > 909) {
            System.out.println("X coordinate can't be higher than 909, try again: ");
            x = readDouble();
        }
        y = readLong();
        return new Coordinates(x, y);
    }

    public long readAge() {
        System.out.println("Write a long for dragon's age");
        long age = readLong();
        while (age < 0) {
            System.out.println("Dragon's age can't be lower than 0, try again: ");
            age = readLong();
        }
        return age;
    }

    public int readWeight() {
        System.out.println("Write an int for dragon's weight");
        int weight = readInt();
        while (weight < 0) {
            System.out.println("Dragon's weight can't be lower than 0, try again: ");
            weight = readInt();
        }
        return weight;
    }

    public Boolean readSpeaking() {
        System.out.println("Write a boolean for dragon's ability to speaking");
        Boolean speaking = readBoolean();
        while (speaking == null) {
            System.out.println("Dragon's ability to speaking can't be null, try again: ");
            speaking = readBoolean();
        }
        return speaking;
    }

    public DragonType readDragonType() {
//        System.out.println("Write a number for dragon's type (1 - WATER, 2 - UNDERGROUND, 3 - AIR, 4 - FIRE)");
//        int dragonTypeNumber;
        DragonType dragonType = null;
//        String value;
        while (!scanner.hasNextLine()) {
            System.out.println("Invalid value, try again: ");
            scanner.next();
        }
        while (dragonType == null) {
            System.out.print("Write a dragon's type (WATER, UNDERGROUND, AIR, FIRE)");
            String value = scanner.nextLine().trim().toUpperCase(); // Приводим к верхнему регистру

            try {
                dragonType = DragonType.valueOf(value); // Проверяем, есть ли в ENUM
            } catch (IllegalArgumentException e) {
                System.out.println(value + " is not an available dragon's type, try again: ");
            }
        }
        //dragonType = scanner.nextLine();
        return dragonType;
    }

    public DragonCave readDragonCave() {
        System.out.println("Write a Float for dragon cave's depth");
        Float depth = readFloat();
        while (depth == null) {
            System.out.println("Dragon cave's depth can't be null, try again: ");
            depth = readFloat();
        }
        System.out.println("Write an int for dragon cave's number of treasures");
        int numberOfTreasures = readInt();
        while (numberOfTreasures < 0) {
            System.out.println("Dragon cave's number of treasures can't be lower than 0, try again: ");
            numberOfTreasures = readInt();
        }
        return new DragonCave(numberOfTreasures, depth);
    }

    public String readString() {
        String value;
        while (true) {
            try {
                value = scanner.nextLine();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid value, try again!");
            }
        }
        return value;
    }

    private double readDouble() {
        double value;
        while (true) {
            try {
                value = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid value, try again!");
            }
        }
        return value;
    }

    private long readLong() {
        long value;
        while (true) {
            try {
                value = Long.parseLong(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid value, try again!");
            }
        }
        return value;
    }

    private int readInt() {
        int value;
        while (true) {
            try {
                value = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid value, try again!");
            }
        }
        return value;
    }

    private Boolean readBoolean() {
        Boolean value;
        while (true) {
            try {
                value = scanner.nextBoolean();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid value, try again!");
                scanner.next();
            }
        }
        return value;
    }

    private Float readFloat() {
        Float value;
        while (true) {
            try {
                value = scanner.nextFloat();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid value, try again!");
            }
        }
        return value;
    }
}