package org.example;

import org.example.system.Client;
import org.example.system.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Main {
    public static void main(String[] args) throws IOException {
//        if (args.length != 2) {
//            System.out.println("Wrong argument");
//            return;
//        }
//        String host = "127.0.0.1";
//        int port = 6651;

        Client client = new Client();
        client.run();
    }
}