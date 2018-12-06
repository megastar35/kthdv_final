package com.fsoft.fa.interviewprocessmanagement.controller;

import com.fsoft.fa.interviewprocessmanagement.model.EntryTest;
import com.fsoft.fa.interviewprocessmanagement.service.DataTableService;
import com.fsoft.fa.interviewprocessmanagement.service.EntryTestService;
import com.fsoft.fa.interviewprocessmanagement.service.PositionService;
import com.fsoft.fa.interviewprocessmanagement.service.SkillService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.fsoft.fa.interviewprocessmanagement.service.DataTableService.GSON;

@Controller
@RequestMapping("/entry-test")
public class EntryTestController {
    private EntryTestService entryTestService;
    private DataTableService dataTableService;
    private PositionService positionService;
    private SkillService skillService;

    @Autowired
    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }

    @Autowired
    public void setSkillService(SkillService skillService) {
        this.skillService = skillService;
    }

    @Autowired
    public void setDataTableService(DataTableService dataTableService) {
        this.dataTableService = dataTableService;
    }

    @Autowired
    public void setEntryTestService(EntryTestService entryTestService) {
        this.entryTestService = entryTestService;
    }

    //Data table

    @GetMapping(value = "/add-entrytest", produces = "application/json")
    @ResponseBody
    public String addEntryTest(@RequestParam(name = "name", defaultValue = "Java") String name) {
        EntryTest newEntryTest = new EntryTest();
        newEntryTest.setName(name);
        entryTestService.saveMyEntryTest(newEntryTest);
        return GSON.toJsonTree(newEntryTest).toString();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public void deleteEntryTest(@PathVariable int id) {
        entryTestService.deleteMyEntryTest(id);
    }

    @PostMapping("/edit/{id}")
    public @ResponseBody
    void editEntryTest(@PathVariable int id, @RequestParam(name = "name") String name) {
        EntryTest entryTestToEdit = entryTestService.getEntryTestById(id);
        entryTestToEdit.setName(name);
    }

    @RequestMapping(value = "/get/all", produces = "application/json")
    public @ResponseBody
    String getAllEntryTests() {
        List<EntryTest> entryTests = entryTestService.showAllEntryTest();
        JsonObject result = new JsonObject();
        result.add("data", GSON.toJsonTree(entryTests));
        return result.toString();
    }

    @PostMapping(value = "/dataTable/create", produces = "application/json")
    public @ResponseBody
    String dataTableAdd(@RequestBody String data) {
        Map<String, Object> json = GSON.fromJson(data, Map.class);
        EntryTest entryTest = new EntryTest();
        entryTest.setName(json.get("name").toString());
        entryTest.setPositions(dataTableService.getPositionsFromJson(json));
        entryTest.setSkills(dataTableService.getSkillsFromJson(json));
        entryTest.setContent(json.get("content").toString());
        entryTestService.saveMyEntryTest(entryTest);
        return dataTableService.populateJsonResult(entryTest).toString();
    }

    @PutMapping(value = "/dataTable/edit", produces = "application/json")
    public @ResponseBody
    String dataTableEdit(@RequestBody String data) {
        System.out.println(data);
        Map<String, Object> json = GSON.fromJson(data, Map.class);
        int id = ((Double) json.get("id")).intValue();
        EntryTest entryTest = entryTestService.getEntryTestById(id);
        entryTest.setName(json.get("name").toString());
        entryTest.setPositions(dataTableService.getPositionsFromJson(json));
        entryTest.setSkills(dataTableService.getSkillsFromJson(json));
        entryTest.setContent(json.get("content").toString());
        entryTestService.saveMyEntryTest(entryTest);
        return dataTableService.populateJsonResult(entryTest).toString();
    }

    /***
     * Since PutMapping doesn't transport payload
     * We use a Post Request to delete data
     * @param data - JSON from client
     * @return empty JsonArray named "data"
     * @see DataTableService#populateJsonResult(Object)
     */
    @PostMapping(value = "/dataTable/delete", produces = "application/json")
    public @ResponseBody
    String dataTableDelete(@RequestBody String data) {
        System.out.println(data);
        Map<String, Object> json = GSON.fromJson(data, Map.class);
        int id = ((Double) json.get("id")).intValue();
        entryTestService.deleteMyEntryTest(id);
        return dataTableService.populateJsonResult(null).toString();
    }

    @GetMapping(value = "/get/{id}")
    @ResponseBody
    public EntryTest getEntryTestById(@PathVariable int id) {
        return entryTestService.getEntryTestById(id);
    }
}
