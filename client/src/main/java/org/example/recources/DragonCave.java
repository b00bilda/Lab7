package org.example.recources;

public class DragonCave {
    private Float depth; //Поле не может быть null
    private int numberOfTreasures; //Значение поля должно быть больше 0

    public DragonCave(int numberOfTreasures, Float depth) {
        this.setNumberOfTreasures(numberOfTreasures);
        this.setDepth(depth);
    }

    public DragonCave() {
    }


    public Float getDepth() {
        return depth;
    }

    public int getNumberOfTreasures() {
        return numberOfTreasures;
    }

    public void setDepth(Float depth) {
        if (depth != null) {
            this.depth = depth;
        } else {
            throw new IllegalArgumentException("");
        }
    }

    public void setNumberOfTreasures(int numberOfTreasures) {
        if (numberOfTreasures > 0) {
            this.numberOfTreasures = numberOfTreasures;
        } else {
            throw new IllegalArgumentException("");
        }
    }
}
