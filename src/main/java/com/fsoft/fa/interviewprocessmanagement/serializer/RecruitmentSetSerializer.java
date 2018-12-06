package com.fsoft.fa.interviewprocessmanagement.serializer;

import com.fsoft.fa.interviewprocessmanagement.model.Recruitment;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Set;

public class RecruitmentSetSerializer implements JsonSerializer<Set<Recruitment>> {
    @Override
    public JsonElement serialize(Set<Recruitment> recruitments, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonSkill = new JsonArray();
        for (Recruitment recruitment : recruitments) {
            JsonObject object = new JsonObject();
            object.addProperty("id", recruitment.getId());
            object.addProperty("name", recruitment.getName());
            object.addProperty("description", recruitment.getDescription());
            jsonSkill.add(object);
        }
        return jsonSkill;
    }
}