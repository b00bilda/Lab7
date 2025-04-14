package org.example.managers.deserializers;

import com.google.gson.*;
import org.example.recources.Coordinates;

import java.lang.reflect.Type;

public class CoordinatesDeserializer implements JsonDeserializer<Coordinates> {
    @Override
    public Coordinates deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject jsonObject = json.getAsJsonObject();

        Coordinates coordinates = new Coordinates();
        if (jsonObject.has("coordinatesX") && !jsonObject.get("coordinatesX").isJsonNull()) {
            coordinates.setX(jsonObject.get("coordinatesX").getAsDouble());
        }
        if (jsonObject.has("coordinatesY") && !jsonObject.get("coordinatesY").isJsonNull()) {
            coordinates.setY(jsonObject.get("coordinatesY").getAsLong());
        }
        return coordinates;
    }
}