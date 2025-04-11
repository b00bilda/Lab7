package org.example.system;

import org.example.recources.Dragon;

import java.io.Serializable;

public class Request implements Serializable {
    private String message;
    private String key = null;
    private Dragon dragon;

    public Request(String message, Dragon dragon, String key) {
        this.message = message;
        this.dragon = dragon;
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public String getKey() {
        return key;
    }

    public Dragon getDragon() {
        return dragon;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }
}
