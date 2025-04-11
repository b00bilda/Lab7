package org.example.system;

import org.example.recources.Dragon;

import java.io.Serializable;

public class Request implements Serializable {
    private String message;
    private String name = null;
    private Dragon dragon;

    public Request(String message, Dragon dragon, String name) {
        this.message = message;
        this.dragon = dragon;
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public Dragon getDragon() {
        return dragon;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }
}
