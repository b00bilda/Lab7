package org.example.managers;

import java.util.Scanner;

public class ServerEnvironment {
    private static ServerEnvironment environment;

    CollectionManager collectionManager;
    CommandManager commandManager;
    //DragonGenerator dragonGenerator;
    FileManager fileManager;
    // Console console;
    Scanner scanner;

    String path = System.getenv("MY_FILE_PATH");

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

//    public DragonGenerator getDragonGenerator() {
//        return dragonGenerator;
//    }

    public FileManager getFileManager() {
        return fileManager;
    }

//    public Console getConsole() {
//        return console;
//    }

    public Scanner getScanner() {
        return scanner;
    }

    public String getPath() {
        return path;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

//    public void setDragonGenerator(DragonGenerator dragonGenerator) {
//        this.dragonGenerator = dragonGenerator;
//    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

//    public void setConsole(Console console) {
//        this.console = console;
//    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
