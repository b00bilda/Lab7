package org.example.system;

import org.example.recources.Dragon;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Stack;

public class ExecuteScript {
    private static Stack<File> stack = new Stack<>();

    public static void execute(String command) {
        File file = new File(command.split(" ")[1]);

        if (stack.contains(file)) {
            System.err.println("Recursion detected");
        }

        if (file.canRead() && file.canWrite()) {
            stack.add(file);
        } else {
            System.err.println("Something wrong with file");
        }

        String fileName = command.split(" ")[1];
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            String line;
            String[] dragonData = new String[8];
            while ((line = br.readLine()) != null) {
                if (line.split(" ")[0].equals("insert")) {
                    String key = line.split(" ")[1];
                    for (int n = 0; n < 7; n++) {
                        if (n == 0) {
                            continue;
                        } else if (n == 3) {
                            dragonData[n] = LocalDateTime.now().toString();
                        } else if ((line = br.readLine()) != null) {
                            dragonData[n] = line;
                        }
                    }
                    Server.sendRequest(new Request("insert " + key, new Dragon(dragonData), key));
                } else if (line.contains("execute_script")) {
                    File anotherFile = new File(line.split(" ")[1]);
                    if (stack.contains(anotherFile)) {
                        System.err.println("Recursion was detected");
                    }
                    if (!file.canRead()) {
                        System.err.println("Something wrong with reading a file");
                    } else {
                        Server.sendRequest(new Request(line, new Dragon(dragonData), null));
                    }
                } else {
                    Server.sendRequest(new Request(line, new Dragon(dragonData), null));
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("File can't be found");
        } catch (IOException e) {
            System.err.println("");
        }
    }

        public String getName() {
            return "execute_script";
        }

        public String getDescription() {
            return "executes script from file";
        }
}
