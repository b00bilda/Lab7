package org.example.managers.deserializers;

import com.google.gson.*;
import org.example.recources.DragonCave;

import java.lang.reflect.Type;

public class DragonCaveDeserializer implements JsonDeserializer<DragonCave> {
    @Override
    public DragonCave deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject jsonObject = json.getAsJsonObject();

        DragonCave dragonCave = new DragonCave();
        if (jsonObject.has("dragonCaveDepth") && !jsonObject.get("dragonCaveDepth").isJsonNull()) {
            dragonCave.setDepth(jsonObject.get("dragonCaveDepth").getAsFloat());
        }
        if (jsonObject.has("dragonCaveNumberOfTreasures") && !jsonObject.get("dragonCaveNumberOfTreasures").isJsonNull()) {
            dragonCave.setNumberOfTreasures(jsonObject.get("dragonCaveNumberOfTreasures").getAsInt());
        }

        return dragonCave;
    }
}
