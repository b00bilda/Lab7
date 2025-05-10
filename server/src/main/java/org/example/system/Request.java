package org.example.system;

import org.example.recources.Dragon;

import java.io.Serializable;

public class Request implements Serializable {
    private String message;
    private String[] args;
    private Dragon dragon;
    private String username = null;
    private String password = null;


    public Request(String message, Dragon dragon, String[] args) {
        this.message = message;
        this.dragon = dragon;
        this.args = args;
    }

    public Request(String message, Dragon dragon, String[] args, String username, String password) {
        this.message = message;
        this.dragon = dragon;
        this.args = args;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public String[] getArgs() {
        return args;
    }

    public Dragon getDragon() {
        return dragon;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }
}
