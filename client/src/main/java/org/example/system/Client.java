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
                String input = scanner.nextLine().trim();
                String command = input.split(" ")[0];

                if (command.equals("exit")) {
                    System.exit(1);
                }
                Dragon dragon = null;
                if (!command.isEmpty()) {
                    dragon = new Dragon();
                    String key = null;
                }

                if (command.equals("insert")) {
                    dragon = dragonGenerator.createDragon();
                }

                if (command.equals("save")) {
                    System.out.println("Save command is not available");
                }

                if (input.split(" ")[0].equals("update")) {
                    long id = Long.parseLong(input.split(" ")[1]);
                    dragon = dragonGenerator.createDragon(id);
                }
//                    if (command.contains("insert") || command.contains("update") || command.contains("replace_if_greater")) {
//                        command = command.split(" ");
//                        DragonGenerator dragonGenerator = new DragonGenerator();
//                        dragon = dragonGenerator.createDragon();
//                        //System.out.println(organization.getId());
//                    } else if (command.contains("execute_script")){
//                        ExecuteScript.execute(command);
//                    }
                    Request request = new Request(input, dragon, null);
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



//    public void startConsoleInput() {
//        try {
//            consoleReader = new BufferedReader(new InputStreamReader(System.in));
//            String input = consoleReader.readLine();
//            if (input.equals("exit") || input.equals("save")){
//                ServerEnvironment.getInstance().getCommandManager().startExecutingServer(new Request(input, null, null));
//            }
//        } catch (IOException e) {
//            System.out.println("");
//        }
//    }

//    private Request readRequest() {
//        try {
//            gson = new Gson();
//            String requestJson = consoleReader.readLine();
//            Request request = gson.fromJson(requestJson, Request.class);
//            return request;
//        } catch (IOException e) {
//            System.out.println("Something wrong with request");
//            return null;
//        }
//    }

    public static void sendRequest(Request request) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonLocalDate()).create();
        String jsonRequest = gson.toJson(request);
        objectOutputStream.writeObject(jsonRequest);
        objectOutputStream.close();
        ByteBuffer buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());


        // Отправляем данные
        while (buffer.hasRemaining()) {
            socket.write(buffer);
        }

        try {
            Request request_server = getAnswer();
            System.out.println("Server answer: \n" + request_server.getMessage());
//        } catch (ClassNotFoundException e) {
//            // Обработка исключения, если класс Request не найден
//            System.out.println("Wrong answer from server");
        } catch (IOException e) {
            // Обработка исключения ввода-вывода при чтении объекта
            System.out.println("Something wrong while reading answer from server");
            System.out.println(e.getMessage());
        }
//       catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
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

//    public static Request getAnswer() throws IOException, InterruptedException, ClassNotFoundException {
//        Thread.sleep(2000);
//        ArrayList<ByteBuffer> bufferList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            ByteBuffer buffer = ByteBuffer.allocate(8192);
//            socket.read(buffer);
//            if (buffer.limit() == buffer.position() || bufferList.isEmpty()) {
//                bufferList.add(buffer);
//            } else {
//                break;
//            }
//        }
//        ByteBuffer bigBuffer = ByteBuffer.allocate(bufferList.size() * 8192);
//        for (ByteBuffer byteBuffer : bufferList) {
//            bigBuffer.put(byteBuffer.array());
//        }
//
//        ByteArrayInputStream bi = new ByteArrayInputStream(bigBuffer.array());
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.socket().getInputStream()));
//        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonLocalDate()).create();
//        String json = reader.readLine();
//        System.out.println("Получено от сервера: " + json);
//
//// И только потом:
//        //Request request = gson.fromJson(json, Request.class);
//        Request request = gson.fromJson(reader, Request.class);
//
//        return request;
//    }

//    private String executeCommand(Request request) {
//        CommandManager manager = ServerEnvironment.getInstance().getCommandManager();
//        return manager.startExecuting(request);
//    }
//
//    private void sendResponse(Request request) {
//        CommandManager manager = ServerEnvironment.getInstance().getCommandManager();
//        try (SocketChannel clientSocket = serverSocketChannel.accept()) {
//            Socket client = clientSocket.socket();
//            clientSocket.configureBlocking(false);
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
//            String responseJson = gson.toJson(executeCommand(request));
//            writer.write(responseJson);
//            writer.newLine();
//            writer.flush();
//        } catch (IOException e) {
//            System.err.println("Something wrong with sending response to client");
//        }
//    }
}
