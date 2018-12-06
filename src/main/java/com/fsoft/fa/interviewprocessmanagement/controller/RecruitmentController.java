package com.fsoft.fa.interviewprocessmanagement.controller;

import com.fsoft.fa.interviewprocessmanagement.model.Position;
import com.fsoft.fa.interviewprocessmanagement.model.Recruitment;
import com.fsoft.fa.interviewprocessmanagement.service.DataTableService;
import com.fsoft.fa.interviewprocessmanagement.service.RecruitmentService;
import com.fsoft.fa.interviewprocessmanagement.service.UploadFileService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.fsoft.fa.interviewprocessmanagement.service.DataTableService.GSON;

@Controller
@RequestMapping("/recruitment")
public class RecruitmentController {

    private RecruitmentService recruitmentService;
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
    public void setRecruitmentService(RecruitmentService recruitmentService) {
        this.recruitmentService = recruitmentService;
    }

    @RequestMapping(value = "/get/all", produces = "application/json")
    public @ResponseBody
    String getAllRecruitments() {
        List<Recruitment> recruitments = recruitmentService.getAll();
        JsonObject result = new JsonObject();
        JsonArray recruitmentJson = (JsonArray) GSON.toJsonTree(recruitments);

        JsonObject files = new JsonObject();
        JsonObject array = new JsonObject();
        uploadFileService.getAll().forEach(file ->
                array.add(String.valueOf(file.getId()), GSON.toJsonTree(file))
        );
        files.add("files", array);
        result.add("files", files);
        result.add("data", recruitmentJson);
        return result.toString();
    }

    @GetMapping(value = "/get/{id}")
    @ResponseBody
    public Recruitment getRecruitmentById(@PathVariable int id) {
        return recruitmentService.getById(id);
    }

    @PostMapping(value = "/dataTable/create", produces = "application/json")
    public @ResponseBody
    String dataTableAdd(@RequestBody String data) {
        Map<String, Object> json = GSON.fromJson(data, Map.class);
        Map<String, Object> avatarJson = (Map<String, Object>) json.get("image");

        Double numberOfRecruits = Double.parseDouble(json.get("numberOfRecruits").toString());
        Recruitment recruitment = new Recruitment();

        if (!avatarJson.get("id").toString().equals("")) {
            recruitment.setImage(dataTableService.getUploadFromJson(avatarJson));
        }

        recruitment.setName(json.get("name").toString());
        recruitment.setStartDate(LocalDate.parse((json.get("startDate").toString())));
        recruitment.setFinishDate(LocalDate.parse((json.get("finishDate").toString())));
        recruitment.setNumberOfRecruits(numberOfRecruits.intValue());
        recruitment.setMajors(dataTableService.getMajorsFromJson(json));
        recruitment.setPositions(dataTableService.getPositionsFromJson(json));
        recruitment.setSkills(dataTableService.getSkillsFromJson(json));
        recruitment.setDescription(json.get("description").toString());
        recruitmentService.save(recruitment);
        return dataTableService.populateJsonResult(recruitment).toString();
    }

    @PutMapping(value = "/dataTable/edit", produces = "application/json")
    public @ResponseBody
    String dataTableEdit(@RequestBody String data) {
        Map<String, Object> json = GSON.fromJson(data, Map.class);
        Map<String, Object> avatarJson = (Map<String, Object>) json.get("image");

        System.out.println(json);
        int id = ((Double) json.get("id")).intValue();
        Double numberOfRecruits = Double.parseDouble(json.get("numberOfRecruits").toString());
        Recruitment recruitment = recruitmentService.getById(id);

        if (!avatarJson.get("id").toString().equals("")) {
            recruitment.setImage(dataTableService.getUploadFromJson(avatarJson));
        }

        Set<Position> positions = dataTableService.getPositionsFromJson(json);
        recruitment.setName(json.get("name").toString());
        recruitment.setStartDate(LocalDate.parse((json.get("startDate").toString())));
        recruitment.setFinishDate(LocalDate.parse((json.get("finishDate").toString())));
        recruitment.setNumberOfRecruits(numberOfRecruits.intValue());
        recruitment.setMajors(dataTableService.getMajorsFromJson(json));
        recruitment.setPositions(positions);
        recruitment.setSkills(dataTableService.getSkillsFromJson(json));
        recruitment.setDescription(json.get("description").toString());
        recruitmentService.save(recruitment);
        return dataTableService.populateJsonResult(recruitment).toString();
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
        recruitmentService.deleteById(id);
        return dataTableService.populateJsonResult(null).toString();
    }
}
