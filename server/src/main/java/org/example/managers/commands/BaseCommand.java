package org.example.managers.commands;

import org.example.system.Request;

public abstract class BaseCommand {
    public final String name;

    protected BaseCommand(String name) {
        this.name = name;
    }


    // public abstract void execute(String[] args);

    public abstract String execute(Request request);


    public String getName() {
        return name;
    }


    public abstract String getDescription();

}
