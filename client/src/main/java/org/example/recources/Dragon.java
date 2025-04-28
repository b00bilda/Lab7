package org.example.recources;

import org.example.system.DragonGenerator;
import org.example.system.IdGenerator;

import java.time.LocalDate;

public class Dragon implements Comparable<Dragon> {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long age; //Значение поля должно быть больше 0
    private int weight; //Значение поля должно быть больше 0
    private Boolean speaking; //Поле может быть null
    private DragonType type; //Поле не может быть null
    private DragonCave cave; //Поле не может быть null

    public Dragon(long id, String name, Coordinates coordinates, long age, int weight, Boolean speaking, DragonType type, DragonCave cave) throws IllegalArgumentException {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("id must be больше 0");
        }
        this.setName(name);
        this.setCoordinates(coordinates);
        this.creationDate = LocalDate.now();
        this.setAge(age);
        this.setWeight(weight);
        this.setSpeaking(speaking);
        this.setType(type);
        this.setCave(cave);
    }

    public Dragon() {
    }

    public Dragon(String[] data) {
        this.id = IdGenerator.generateId();
        this.setName(data[0]);
        this.setCoordinates(new Coordinates(Double.parseDouble(data[1]), Long.parseLong(data[2])));
        this.creationDate = LocalDate.now();
        this.setAge(Long.parseLong(data[3]));
        this.weight = Integer.parseInt(data[4]);
        this.speaking = Boolean.parseBoolean(data[5]);
        this.type = DragonType.valueOf(data[6].toUpperCase());
        this.cave = new DragonCave(Integer.parseInt(data[7]), Float.parseFloat(data[8]));
    }

    public Dragon(Long id) {
        this.id = id;
    }

    public void setID(long id) {
        this.id = id;
    }


    public void setName(String name) {
        if (name != null || name.isBlank()) {
            this.name = name;
        } else {
            throw new NullPointerException("Argument is incorrect");
        }
    }


    public void setCoordinates(Coordinates coordinates) {
        if (coordinates != null) {
            this.coordinates = coordinates;
        } else {
            throw new IllegalArgumentException("Argument is incorrect");
        }
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }


    public void setAge(long age) {
        if (age > 0) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Argument is incorrect");
        }
    }

    public void setWeight(int weight) {
        if (weight > 0) {
            this.weight = weight;
        } else {
            throw new IllegalArgumentException("Argument is incorrect");
        }
    }

    public void setSpeaking(Boolean speaking) {
        if (speaking != null) {
            this.speaking = speaking;
        } else {
            throw new IllegalArgumentException("Argument is incorrect");
        }
    }

    public void setType(DragonType type) {
        if (type != null) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("Argument is incorrect");
        }
    }

    public void setCave(DragonCave cave) {
        if (cave != null) {
            this.cave = cave;
        } else {
            throw new IllegalArgumentException("Argument is incorrect");
        }
    }

    public long getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public long getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public Boolean getSpeaking() {
        return speaking;
    }

    public DragonType getType() {
        return type;
    }

    public DragonCave getCave() {
        return cave;
    }

    @Override
    public String toString() {
        return "Dragon{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", coordinateX='" + coordinates.getX() + '\'' +
                ", coordinateY='" + coordinates.getY() + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", age='" + age + '\'' +
                ", weight='" + weight + '\'' +
                ", speaking='" + speaking + '\'' +
                ", type='" + type + '\'' +
                ", caveDepth='" + cave.getDepth() + '\'' +
                ", caveNumberOfTreasures='" + cave.getNumberOfTreasures() + '\'' +
                '}';
    }

    @Override
    public int compareTo(Dragon other) {
        return Long.compare(age, other.age);
    }
}
