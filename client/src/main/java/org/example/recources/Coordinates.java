package org.example.recources;

public class Coordinates {
    private double x; //Максимальное значение поля: 909
    private long y;

    public Coordinates(double x, long y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
    }

    public double getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public void setX(double x) {
        if (x < 909) {
            this.x = x;
        } else {
            throw new IllegalArgumentException("x cannot be greater than 909.");
        }
    }

    public void setY(long y) {
        this.y = y;
    }
}
