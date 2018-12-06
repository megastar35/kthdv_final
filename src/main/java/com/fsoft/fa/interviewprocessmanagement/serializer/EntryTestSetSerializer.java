package com.fsoft.fa.interviewprocessmanagement.serializer;

import com.fsoft.fa.interviewprocessmanagement.model.EntryTest;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Set;

public class EntryTestSetSerializer implements JsonSerializer<Set<EntryTest>> {
    @Override
    public JsonElement serialize(Set<EntryTest> entryTests, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonSkill = new JsonArray();
        for (EntryTest entryTest : entryTests) {
            JsonObject object = new JsonObject();
            object.addProperty("id", entryTest.getId());
            object.addProperty("name", entryTest.getName());
            object.addProperty("content", entryTest.getContent());
            jsonSkill.add(object);
        }
        return jsonSkill;
    }
}
