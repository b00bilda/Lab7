package org.example.managers.deserializers;

import com.google.gson.*;
import org.example.recources.Coordinates;
import org.example.recources.Dragon;
import org.example.recources.DragonCave;
import org.example.recources.DragonType;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DragonDeserializer implements JsonDeserializer<Dragon> {
    @Override
    public Dragon deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject jsonObject = json.getAsJsonObject();

        Dragon dragon = new Dragon();
        if (jsonObject.has("id") && !jsonObject.get("id").isJsonNull()) {
            String dateStr = jsonObject.get("id").getAsString();
            dragon.setID(Long.parseLong(dateStr));
        }
        if (jsonObject.has("name") && !jsonObject.get("name").isJsonNull()) {
            dragon.setName(jsonObject.get("name").getAsString());
        }
        if (jsonObject.has("coordinates") && !jsonObject.get("coordinates").isJsonNull()) {
            dragon.setCoordinates((Coordinates) context.deserialize(jsonObject.get("coordinates"), Coordinates.class));
        } else {
            dragon.setCoordinates(new Coordinates(jsonObject.get("coordinatesX").getAsDouble(), jsonObject.get("coordinatesY").getAsLong()));  // или null, если допустимо
        }
        dragon.setCreationDate((LocalDate) context.deserialize(jsonObject.get("creationDate"), LocalDateTime.class));
        if (jsonObject.has("age") && !jsonObject.get("age").isJsonNull()) {
            dragon.setAge(jsonObject.get("age").getAsLong());
        }
        if (jsonObject.has("weight") && !jsonObject.get("weight").isJsonNull()) {
            dragon.setWeight(jsonObject.get("weight").getAsInt());
        }
        if (jsonObject.has("speaking") && !jsonObject.get("speaking").isJsonNull()) {
            dragon.setSpeaking(jsonObject.get("speaking").getAsBoolean());
        }
        if (jsonObject.has("dragonType") && !jsonObject.get("dragonType").isJsonNull()) {
            dragon.setType((DragonType) context.deserialize(jsonObject.get("dragonType"), DragonType.class));
        }
        if (jsonObject.has("dragonCave") && !jsonObject.get("dragonCave").isJsonNull()) {
            dragon.setCave((DragonCave) context.deserialize(jsonObject.get("dragonCave"), DragonCave.class));
        } else {
            dragon.setCave(new DragonCave(jsonObject.get("dragonCaveNumberOfTreasures").getAsInt(), jsonObject.get("dragonCaveDepth").getAsFloat()));  // или null, если допустимо
        }

        return dragon;
    }
}
