package com.fsoft.fa.interviewprocessmanagement.serializer;

import com.fsoft.fa.interviewprocessmanagement.model.Position;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Set;

public class PositionSetSerializer implements JsonSerializer<Set<Position>> {
    @Override
    public JsonElement serialize(Set<Position> positions, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonSkill = new JsonArray();
        for (Position position : positions) {
            JsonObject object = new JsonObject();
            object.addProperty("id", position.getId());
            object.addProperty("name", position.getName());
            object.addProperty("salary", position.getSalary());
            jsonSkill.add(object);
        }
        return jsonSkill;
    }
}