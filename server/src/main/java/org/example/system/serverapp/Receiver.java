package org.example.system.serverapp;

import org.example.managers.ServerEnvironment;
import org.example.system.Request;

public class Receiver {
    public static String clear(Request request) {
        if (request.getMessage().split("").length == 1) {
            ServerEnvironment.getInstance().getCollectionManager().getCollection().clear();
            return "Collection is cleared";
        } else {
            throw new IllegalArgumentException("command parameter");
        }
    }
}

