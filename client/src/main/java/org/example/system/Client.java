package org.example.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.recources.Dragon;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Client {
    private static SocketChannel socket;
    private SocketChannel serverSocketChannel;
    String filePath = System.getenv("MY_FILE_PATH");
    private File file;
    BufferedReader consoleReader;
    //Gson gson;


    public void initialize(String host, int port) throws IOException {
        try {
            InetSocketAddress address = new InetSocketAddress(host, port); // создаем адрес сокета (IP-адрес и порт)
            socket = SocketChannel.open();
            socket.connect(address);
            socket.configureBlocking(false); // неблокирующий режим ввода-вывода
        } catch (RuntimeException e) {
            System.out.println("Server " + host + " on port " + port + " is not available");
            System.exit(1);
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonLocalDate()).create();
        DragonGenerator dragonGenerator = new DragonGenerator();
        System.out.println("Welcome to app!");
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] commandLine = input.split(" ");
            String[] arguments = Arrays.copyOfRange(commandLine, 1, commandLine.length);
            System.out.println(arguments.toString());
            String command = commandLine[0];
            Request request = null;
            Dragon dragon = null;

            if (!command.isEmpty()) {
                switch (command) {
                    case "exit":
                        System.exit(1);
                        break;
                    case "save":
                        System.out.println("Save command is not available");
                        break;
                    case "update":
                        long id = Long.parseLong(arguments[0]);
                        dragon = dragonGenerator.createDragon(id);
                        break;
                    case "insert":
                        dragon = dragonGenerator.createDragon();
                        break;
                    case "execute_script":
                        ExecuteScript.execute(arguments[0]);
                        break;
                    default:
                        dragon = null;
                        break;
                }

                request = new Request(input, dragon, arguments);


                String jsonRequest = gson.toJson(request, Request.class) + "\n";
                try (SocketChannel channel = SocketChannel.open()) {
                    channel.connect(new InetSocketAddress("localhost", 6651));
                    ByteBuffer buffer = ByteBuffer.wrap(jsonRequest.getBytes(StandardCharsets.UTF_8));
                    channel.write(buffer);

                    ByteBuffer responseBuffer = ByteBuffer.allocate(8192);
                    channel.read(responseBuffer);
                    responseBuffer.flip();
                    String responseJson = StandardCharsets.UTF_8.decode(responseBuffer).toString();
                    Response response = gson.fromJson(responseJson.trim(), Response.class);
                    System.out.println(response.getMessage());

                } catch (IOException e) {
                    System.out.println("Ошибка подключения к серверу: " + e.getMessage());
                }
            }
        }
    }


    public static void sendRequest(Request request) throws IOException {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonLocalDate()).create();
        String jsonRequest = gson.toJson(request, Request.class) + "\n";
        try (SocketChannel channel = SocketChannel.open()) {
            channel.connect(new InetSocketAddress("localhost", 6651));
            ByteBuffer buffer = ByteBuffer.wrap(jsonRequest.getBytes(StandardCharsets.UTF_8));
            channel.write(buffer);

            ByteBuffer responseBuffer = ByteBuffer.allocate(8192);
            channel.read(responseBuffer);
            responseBuffer.flip();
            String responseJson = StandardCharsets.UTF_8.decode(responseBuffer).toString();
            Response response = gson.fromJson(responseJson.trim(), Response.class);
            System.out.println(response.getMessage());

        } catch (IOException e) {
            System.out.println("Ошибка подключения к серверу: " + e.getMessage());
        }
    }



    public static Request getAnswer() throws IOException {
        Selector selector = Selector.open();
        socket.register(selector, SelectionKey.OP_READ); // регистрируем интерес к чтению

        ByteBuffer buffer = ByteBuffer.allocate(8192);
        StringBuilder messageBuilder = new StringBuilder();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new JsonLocalDate())
                .create();

        while (true) {
            // ждём, пока что-то будет доступно
            if (selector.select(5000) == 0) {
                System.out.println("Нет ответа от сервера...");
                continue;
            }

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    buffer.clear();

                    int bytesRead = channel.read(buffer);
                    if (bytesRead == -1) {
                        channel.close();
                        throw new IOException("Сервер закрыл соединение");
                    }

                    buffer.flip();
                    String part = StandardCharsets.UTF_8.decode(buffer).toString();
                    messageBuilder.append(part);

                    // Простейшая проверка окончания сообщения (например, по символу \n)
                    if (part.contains("\n")) {
                        selector.close();
                        String json = messageBuilder.toString().trim();
                        System.out.println("Получено от сервера: " + json);
                        return gson.fromJson(json, Request.class);
                    }
                }

                keyIterator.remove();
            }
        }
    }

}
