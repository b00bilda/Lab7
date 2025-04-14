package org.example.managers;

import java.util.Scanner;

public class ServerEnvironment {
    private static ServerEnvironment environment;

    CollectionManager collectionManager;
    CommandManager commandManager;
    FileManager fileManager;

    public static ServerEnvironment getInstance() {
        if (environment == null) {
            environment = new ServerEnvironment();
        }
        return environment;
    }

    private ServerEnvironment() {}

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public FileManager getFileManager() {
        return fileManager;
    }


    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }
}
