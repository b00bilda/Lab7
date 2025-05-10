package org.example;

import org.example.managers.*;
import org.example.system.Server;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainServer {
    public static final String URL = "jdbc:postgresql://localhost:6651/dragons";

    public static void main(String[] args) {

        ServerEnvironment serverEnvironment = ServerEnvironment.getInstance();
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager();
        FileManager fileManager = new FileManager();

        serverEnvironment.setCollectionManager(collectionManager);
        serverEnvironment.setCommandManager(commandManager);
        serverEnvironment.setFileManager(fileManager);

        DatabaseManager.setURL(URL);
        DatabaseManager.setUsername("postgres");
        DatabaseManager.setPassword("71886228");
        DatabaseManager.connectToDatabase();;

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

        Server server = new Server(6652);
        server.run();
    }
}