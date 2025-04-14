package org.example.managers.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.example.recources.DragonType;

import java.lang.reflect.Type;

public class DragonTypeDeserializer implements JsonDeserializer<DragonType> {
    @Override
    public DragonType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        String dragonTypeString = json.getAsString();
        return DragonType.valueOf(dragonTypeString);

    }
}
