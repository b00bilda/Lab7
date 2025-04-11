package org.example.managers.commands;

public abstract class BaseCommand {
    public final String name;

    protected BaseCommand(String name) {
        this.name = name;
    }


    public abstract void execute(String[] args);


    public String getName() {
        return name;
    }


    public abstract String getDescription();

}
