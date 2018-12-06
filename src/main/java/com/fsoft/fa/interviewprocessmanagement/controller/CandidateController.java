package com.fsoft.fa.interviewprocessmanagement.controller;

import com.fsoft.fa.interviewprocessmanagement.model.Candidate;
import com.fsoft.fa.interviewprocessmanagement.model.Recruitment;
import com.fsoft.fa.interviewprocessmanagement.service.CandidateService;
import com.fsoft.fa.interviewprocessmanagement.service.DataTableService;
import com.fsoft.fa.interviewprocessmanagement.service.UploadFileService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.fsoft.fa.interviewprocessmanagement.service.DataTableService.GSON;

@Controller
@RequestMapping("/candidate")
public class CandidateController {
    private CandidateService candidateService;
    private DataTableService dataTableService;
    private UploadFileService uploadFileService;

    @Autowired
    public void setUploadFileService(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

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
        JsonObject result = new JsonObject();
        JsonObject files = new JsonObject();
        JsonObject array = new JsonObject();
        uploadFileService.getAll().forEach(file ->
                array.add(String.valueOf(file.getId()), GSON.toJsonTree(file))
        );
        files.add("files", array);
        result.add("files", files);
        result.add("data", GSON.toJsonTree(candidateService.getAllCandidates()));
        return result.toString();
    }

    @PostMapping(value = "/dataTable/create", produces = "application/json")
    public @ResponseBody
    String dataTableAdd(@RequestBody String data) {
        Map<String, Object> json = GSON.fromJson(data, Map.class);
        Map<String, Object> recruitmentJson = (Map<String, Object>) json.get("recruitment");
        Map<String, Object> avatarJson = (Map<String, Object>) json.get("image");
        Map<String, Object> cvJson = (Map<String, Object>) json.get("curriculumVitae");
        System.out.println(json);
        Recruitment recruitment = dataTableService.getRecruitmentFromJson(recruitmentJson);
        Set<String> certificates = new HashSet<>(Arrays
                .asList(json.get("certificates").toString().split(", *|\n- *")));
        Set<String> experience = new HashSet<>(Arrays
                .asList(json.get("experience").toString().split(", *|\n- *")));
        Candidate candidate = new Candidate();

        if (!cvJson.get("id").toString().equals("")) {
            candidate.setCurriculumVitae(dataTableService.getUploadFromJson(cvJson));
        }
        if (!avatarJson.get("id").toString().equals("")) {
            candidate.setImage(dataTableService.getUploadFromJson(avatarJson));
        }
        candidate.setCertificates(certificates);
        candidate.setExperience(experience);
        candidate.setName(json.get("name").toString());
        candidate.setEmail(json.get("email").toString());
        candidate.setAddress(json.get("address").toString());
        candidate.setDescription(json.get("description").toString());
        candidate.setPositions(dataTableService.getPositionsFromJson(json));
        candidate.setSkills(dataTableService.getSkillsFromJson(json));
        candidate.setDayOfBirth(LocalDate.parse(json.get("dayOfBirth").toString()));
        candidate.setPhoneNumber(((Double) Double.parseDouble(json.get("phoneNumber").toString())).longValue());

        recruitment.addCandidate(candidate);
        candidateService.save(candidate);
        return dataTableService.populateJsonResult(candidate).toString();
    }


    @PutMapping(value = "/dataTable/edit", produces = "application/json")
    public @ResponseBody
    String dataTableEdit(@RequestBody String data) {

        Map<String, Object> json = GSON.fromJson(data, Map.class);
        System.out.println(json);
        Set<String> certificates = new HashSet<>(Arrays
                .asList(json.get("certificates").toString().split(", *|\n- *")));
        Set<String> experience = new HashSet<>(Arrays
                .asList(json.get("experience").toString().split(", *|\n- *")));

        Map<String, Object> recruitmentJson = (Map<String, Object>) json.get("recruitment");
        Map<String, Object> avatarJson = (Map<String, Object>) json.get("image");
        Map<String, Object> cvJson = (Map<String, Object>) json.get("curriculumVitae");
        int id = ((Double) json.get("id")).intValue();
        Recruitment recruitment = dataTableService.getRecruitmentFromJson(recruitmentJson);
        Candidate candidate = candidateService.getById(id);

        candidate.setCertificates(certificates);
        candidate.setExperience(experience);

        if (!cvJson.get("id").toString().equals("")) {
            candidate.setCurriculumVitae(dataTableService.getUploadFromJson(cvJson));
        }
        if (!avatarJson.get("id").toString().equals("")) {
            candidate.setImage(dataTableService.getUploadFromJson(avatarJson));
        }
        candidate.setEmail(json.get("email").toString());
        candidate.setName(json.get("name").toString());
        candidate.setAddress(json.get("address").toString());

        candidate.setDescription(json.get("description").toString());
        candidate.setPositions(dataTableService.getPositionsFromJson(json));
        candidate.setSkills(dataTableService.getSkillsFromJson(json));

        candidate.setDayOfBirth(LocalDate.parse(json.get("dayOfBirth").toString()));
        candidate.setPhoneNumber(((Double) Double.parseDouble(json.get("phoneNumber").toString())).longValue());
        candidate.setPotential(Boolean.parseBoolean((json.get("potential").toString())));
        recruitment.addCandidate(candidate);

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
