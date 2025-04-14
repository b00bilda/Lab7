package org.example.managers.serializers;

import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.example.recources.Dragon;

import java.lang.reflect.Type;

public class DragonSerializer implements JsonSerializer<Dragon> {
    @Override
    public JsonObject serialize(Dragon src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();

        result.addProperty("id", src.getID());
        result.addProperty("name", src.getName());
        result.add("coordinates", context.serialize(src.getCoordinates()));
        result.add("creationDate", context.serialize(src.getCreationDate()));
        result.addProperty("age", src.getAge());
        result.addProperty("weight", src.getWeight());
        result.addProperty("speaking", src.getSpeaking());
        result.add("dragonType", context.serialize(src.getType()));
        result.add("dragonCave", context.serialize(src.getCave()));

        return result;
    }
}
