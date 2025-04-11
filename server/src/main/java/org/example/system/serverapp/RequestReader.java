package org.example.system.serverapp;

import org.example.system.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.google.gson.Gson;


public class RequestReader {
    private final BufferedReader reader;
    private final Gson gson = new Gson();

    public RequestReader(InputStream inputStream) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public Request readRequest() throws IOException {
        String json = reader.readLine();
        return gson.fromJson(json, Request.class);
    }
}
