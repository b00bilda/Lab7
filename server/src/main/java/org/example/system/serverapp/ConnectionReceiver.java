package org.example.system.serverapp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;

public class ConnectionReceiver {
    private ServerSocket serverSocket;
    private ServerSocketChannel serverSocketChannel;
    private static int port = 6651;

    public void initialize() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            System.out.println("Server was launched. Port: " + port);
        } catch (IOException exception) {
            System.out.println("Server launch error.!");
            System.out.println(exception.getMessage());
        }
    }
}
