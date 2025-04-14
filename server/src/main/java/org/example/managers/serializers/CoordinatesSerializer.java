package org.example.managers.serializers;

import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.example.recources.Coordinates;

import java.lang.reflect.Type;

public class CoordinatesSerializer implements JsonSerializer<Coordinates> {
    @Override
    public JsonObject serialize(Coordinates src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();

        result.addProperty("coordinatesX", src.getX());
        result.addProperty("coordinatesY", src.getY());

        return result;
    }
}
