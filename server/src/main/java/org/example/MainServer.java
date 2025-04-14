package org.example;

import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.managers.FileManager;
import org.example.managers.ServerEnvironment;
import org.example.system.Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainServer {
    public static void main(String[] args) {
        ServerEnvironment serverEnvironment = ServerEnvironment.getInstance();
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager();
        FileManager fileManager = new FileManager();

        serverEnvironment.setCollectionManager(collectionManager);
        serverEnvironment.setCommandManager(commandManager);
        serverEnvironment.setFileManager(fileManager);

        String filePath = System.getenv("MY_FILE_PATH");
        if (filePath == null) {
            System.err.println("Environmental variable MY_FILE_PATH wasn't founded");
            return;
        }

        System.out.println("Reading a file " + filePath);

        File file = new File(filePath);
        if (!file.exists() || !file.canRead()) {
            System.err.println("Can't read a file " + filePath);
            return;
        }
        if (file.canRead() && file.canWrite()) {
            System.out.println("Downloading data from file.");
            try {
                ServerEnvironment.getInstance().getFileManager().readFile(filePath);
                System.out.println("Data was downloaded");
            } catch (Exception e) {
                System.out.println("Something wrong with reading a file");
            }
        }

        Server server = new Server(6651);
        server.run();
    }
}