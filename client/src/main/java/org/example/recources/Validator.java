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

    public static void validateType(String arg) {
        try {
            DragonType.valueOf(arg);
        } catch (IllegalArgumentException e) {
            System.out.println();
        }
    }

    public static void validateName(String arg) {
        try {
            ;
        } catch (IllegalArgumentException e) {
            System.out.println();
        }
    }

    public static void validateCaveNumberOfTreasures(String arg) {
        try {
            int numberOfTreasures = Integer.parseInt(arg);
            if (numberOfTreasures <= 0) {
                throw new IllegalArgumentException("Number of treasure");
            }
        } catch (IllegalArgumentException e) {
            System.out.println();
        }
    }

    public static void validateCaveDepth(String arg){
        try {
            Float depth = Float.parseFloat(arg);
            if (arg == null) {
                throw new IllegalArgumentException("depth cannot be null");
            }
        }catch (IllegalArgumentException e) {
            System.out.println();
        }
    }

    public static void validateSpeaking(){
        try{

        }catch (IllegalArgumentException e){
            System.out.println();
        }
    }

}
