package com.github.indiv0.radio.serialization;

import java.lang.reflect.Type;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import ashulman.typesafety.gson.GsonTypeAdapter;

import com.github.indiv0.radio.util.SupplimentaryTypes;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;

public class FrequencyTypeAdapter implements GsonTypeAdapter<Location> {

    private static final Type TYPE = SupplimentaryTypes.LOCATION;

    @Override
    public JsonElement serialize(Location src, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray arr = new JsonArray();
        arr.add(new JsonPrimitive(src.getWorld().getName()));
        arr.add(new JsonPrimitive(src.getX()));
        arr.add(new JsonPrimitive(src.getY()));
        arr.add(new JsonPrimitive(src.getZ()));

        System.out.println("serializing a radio!" + arr.toString());
        return arr;
    }

    @Override
    public Location deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray arr = json.getAsJsonArray();
        World w = Bukkit.getWorld(arr.get(0).getAsString());
        return new Location(w, arr.get(1).getAsDouble(), arr.get(2).getAsDouble(), arr.get(3).getAsDouble());
    }

    @Override
    public Type getType() {
        return TYPE;
    }
}