package com.fsoft.fa.interviewprocessmanagement.serializer;

import com.fsoft.fa.interviewprocessmanagement.model.Candidate;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Set;

public class CandidateSetSerializer implements JsonSerializer<Set<Candidate>> {
    @Override
    public JsonElement serialize(Set<Candidate> candidates, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonSkill = new JsonArray();
        for (Candidate candidate : candidates) {
            JsonObject object = new JsonObject();
            object.addProperty("id", candidate.getId());
            object.addProperty("name", candidate.getName());
            jsonSkill.add(object);
        }
        return jsonSkill;
    }
}
