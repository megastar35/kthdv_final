package com.fsoft.fa.interviewprocessmanagement.serializer;

import com.fsoft.fa.interviewprocessmanagement.model.Major;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Set;

public class MajorSetSerializer implements JsonSerializer<Set<Major>> {
    @Override
    public JsonElement serialize(Set<Major> majors, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonSkill = new JsonArray();
        for (Major major : majors) {
            JsonObject object = new JsonObject();
            object.addProperty("id", major.getId());
            object.addProperty("name", major.getName());
            object.addProperty("description", major.getDescription());
            jsonSkill.add(object);
        }
        return jsonSkill;
    }
}

