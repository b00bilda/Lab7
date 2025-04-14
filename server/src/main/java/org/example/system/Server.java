package org.example.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.managers.CommandManager;
import org.example.managers.FileManager;
import org.example.managers.ServerEnvironment;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Scanner;

public class Server {
    Gson gson;
    int port;

    public Server(int port) {
        this.port = port;
        this.gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonLocalDate()).create();
    }

    public void run() {
        try (Selector selector = Selector.open();
             ServerSocketChannel serverChannel = ServerSocketChannel.open()) {

            serverChannel.bind(new InetSocketAddress(6651));
            serverChannel.configureBlocking(false);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("Server was launched, port: " + port);

            while (true) {
                selector.select();
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();

                    if (key.isAcceptable()) {
                        SocketChannel clientChannel = serverChannel.accept();
                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_READ, new StringBuilder());
                        System.out.println("Connecting client: " + clientChannel.getRemoteAddress());
                    }

                    if (key.isReadable()) {
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        StringBuilder data = (StringBuilder) key.attachment();
                        ByteBuffer buffer = ByteBuffer.allocate(8192);

                        int bytesRead = clientChannel.read(buffer);
                        if (bytesRead == -1) {
                            clientChannel.close();
                            continue;
                        }

                        buffer.flip();
                        String received = StandardCharsets.UTF_8.decode(buffer).toString();
                        data.append(received);

                        if (received.contains("\n")) {
                            String json = data.toString().trim();
                            System.out.println("Recieved: " + json);

                            Request request = gson.fromJson(json, Request.class);
                            CommandManager commandManager = ServerEnvironment.getInstance().getCommandManager();
                            String result = commandManager.startExecuting(request);
                            System.out.println(result);
                            Response response = new Response(result);

                            String responseJson = gson.toJson(response) + "\n";
                            ByteBuffer responseBuffer = ByteBuffer.wrap(responseJson.getBytes(StandardCharsets.UTF_8));
                            clientChannel.write(responseBuffer);
                            clientChannel.close();

                            System.out.println("Response was sent");
                        }
                    }

                    keyIterator.remove();
                }
            }

        } catch (IOException e) {
            System.err.println("Server initialization error " + e.getMessage());
        }
    }
}
