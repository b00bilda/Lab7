package org.example.system.serverapp;

import com.google.gson.Gson;
import org.example.system.Response;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

class ResponseSender {
    private final BufferedWriter writer;
    private final Gson gson = new Gson();

    public ResponseSender(OutputStream outputStream) {
        this.writer = new BufferedWriter(new OutputStreamWriter(outputStream));
    }

    public void sendResponse(Response response) throws IOException {
        String json = gson.toJson(response);
        writer.write(json);
        writer.newLine();
        writer.flush();
    }
}
