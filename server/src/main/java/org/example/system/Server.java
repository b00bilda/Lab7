package org.example.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.managers.*;
import org.example.recources.Dragon;

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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Server implements Runnable {
    Gson gson;
    int port;
    private static Set<String> registeredUsers = new HashSet<>();
    private final ExecutorService readerPool = Executors.newCachedThreadPool();
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();
    private final ConcurrentHashMap<String, Dragon> dragonsCollection = new ConcurrentHashMap<>();

    public Server(int port) {
        this.port = port;
        this.gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonLocalDate()).create();
    }

    public void run() {
        CollectionManager manager = ServerEnvironment.getInstance().getCollectionManager();
        manager.setTable(DatabaseManager.getCollectionFromDatabase());
        try (Selector selector = Selector.open();
             ServerSocketChannel serverChannel = ServerSocketChannel.open()) {

            serverChannel.bind(new InetSocketAddress(6652));
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
                        readerPool.submit(() -> {
                            SocketChannel clientChannel = (SocketChannel) key.channel();
                            StringBuilder data = (StringBuilder) key.attachment();
                            ByteBuffer buffer = ByteBuffer.allocate(8192);

                            try {
                                int bytesRead = clientChannel.read(buffer);
                                if (bytesRead == -1) {
                                    clientChannel.close();
                                    return;
                                }

                                buffer.flip();
                                String received = StandardCharsets.UTF_8.decode(buffer).toString();
                                data.append(received);

                                if (received.contains("\n")) {
                                    String json = data.toString().trim();
                                    System.out.println("Recieved: " + json);

                                    Request request = gson.fromJson(json, Request.class);
                                    CommandManager commandManager = ServerEnvironment.getInstance().getCommandManager();
                                    forkJoinPool.submit(() -> {
                                        String result = commandManager.startExecuting(request);
                                        System.out.println(result);
                                        Response response = new Response(result);

                                        new Thread(() -> {
                                            sendResponse(clientChannel, response);
                                        }).start();
                                    });
                                }
                            } catch (IOException e) {
                                System.err.println(e.getMessage() + "Something wrong with reading from client");
                            }
                        });
                        keyIterator.remove();
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Server initialization error " + e.getMessage());
        }
    }

        private void sendResponse(SocketChannel clientChannel, Response response) {
            try {
                String responseJson = gson.toJson(response) + "\n";
                ByteBuffer responseBuffer = ByteBuffer.wrap(responseJson.getBytes(StandardCharsets.UTF_8));
                clientChannel.write(responseBuffer);
                clientChannel.close();

                System.out.println("Response was sent");
            } catch (IOException e) {
                System.err.println("Error sending response: " + e.getMessage());
            }
        }

    public static void addUser(String username) {
        registeredUsers.add(username);
    }
}
