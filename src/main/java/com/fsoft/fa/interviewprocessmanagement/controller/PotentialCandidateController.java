package com.fsoft.fa.interviewprocessmanagement.controller;

import com.fsoft.fa.interviewprocessmanagement.model.Candidate;
import com.fsoft.fa.interviewprocessmanagement.service.CandidateService;
import com.fsoft.fa.interviewprocessmanagement.service.DataTableService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.fsoft.fa.interviewprocessmanagement.service.DataTableService.GSON;

@Controller
@RequestMapping("/potential")
public class PotentialCandidateController {
    private CandidateService candidateService;
    private DataTableService dataTableService;

    @Autowired
    public void setDataTableService(DataTableService dataTableService) {
        this.dataTableService = dataTableService;
    }

    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }


    @RequestMapping(value = "/get/all", produces = "application/json")
    public @ResponseBody
    String getAllCandidates() {
        List<Candidate> candidates = candidateService.getPotentialCandidates();
        JsonObject result = new JsonObject();
        JsonArray json = (JsonArray) GSON.toJsonTree(candidates);
        result.add("data", json);
        return result.toString();
    }

    @PutMapping(value = "/dataTable/edit", produces = "application/json")
    public @ResponseBody
    String dataTableEdit(@RequestBody String data) {
        Map<String, Object> json = GSON.fromJson(data, Map.class);
        Map<String, Object> recruitmentJson = (Map<String, Object>) json.get("recruitment");
        int id = ((Double) json.get("id")).intValue();
        System.out.println(json);
//        Set<String> certificates = new HashSet<>(Arrays
//                .asList(json.get("certificates").toString().split(", *|\n- *")));
//        Set<String> experience = new HashSet<>(Arrays
//                .asList(json.get("experience").toString().split(", *|\n- *")));
//        Recruitment recruitment = dataTableService.getRecruitmentFromJson(recruitmentJson);
//        System.out.println("Certificates: " + certificates);
//        System.out.println("Experience: " + experience);

        Set<Double> testScores = Arrays.asList(json.get("testScores").toString()
                .split(", *|\n- *"))
                .stream()
                .filter(x -> !x.equals(""))
                .map(score -> Double.parseDouble(score))
                .collect((Collectors.toSet()));

        Candidate candidate = candidateService.getById(id);
        candidate.setSuitable(Boolean.parseBoolean(json.get("suitable").toString()));
        candidate.setEntryTests(dataTableService.getEntryTestsFromJson(json));
        candidate.setTestScores(testScores);
        candidateService.save(candidate);

        return dataTableService.populateJsonResult(candidate).toString();
    }

    /***
     * Since PutMapping doesn't transport payload
     * We use a Post Request to delete data
     * @param data - JSON from client
     * @return empty JsonArray named "data"
     */
    @PostMapping(value = "/dataTable/delete", produces = "application/json")
    public @ResponseBody
    String dataTableDelete(@RequestBody String data) {
        System.out.println(data);
        Map<String, Object> json = GSON.fromJson(data, Map.class);
        int id = ((Double) json.get("id")).intValue();
        candidateService.deleteById(id);
        return dataTableService.populateJsonResult(null).toString();
    }
}
