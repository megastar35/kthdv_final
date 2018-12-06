package com.fsoft.fa.interviewprocessmanagement.controller;

import com.fsoft.fa.interviewprocessmanagement.model.Major;
import com.fsoft.fa.interviewprocessmanagement.model.Position;
import com.fsoft.fa.interviewprocessmanagement.service.DataTableService;
import com.fsoft.fa.interviewprocessmanagement.service.MajorService;
import com.fsoft.fa.interviewprocessmanagement.service.PositionService;
import com.fsoft.fa.interviewprocessmanagement.service.SkillService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.fsoft.fa.interviewprocessmanagement.service.DataTableService.GSON;

@RequestMapping("/position")
@Controller
public class PositionController {

    private final String PATH = "position";
    private MajorService majorService;
    private PositionService positionService;
    private SkillService skillService;
    private DataTableService dataTableService;

    @Autowired
    public void setMajorService(MajorService majorService) {
        this.majorService = majorService;
    }

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

    @RequestMapping
    public String showPosition() {
        return PATH;
    }

    @RequestMapping("/create-position")
    public String createPosition(Model model) {
        model.addAttribute("mode", "MODE_CREATE_POSTION");
        return PATH;
    }

    @PostMapping("/save-position")
    public String addPosition(@ModelAttribute Position position, Model model) {
        positionService.save(position);
        model.addAttribute("mode", "MODE_HOME");
        return PATH;
    }

    @GetMapping("/show-postions")
    public String showAllPositions(Model model) {
        model.addAttribute("positions", positionService.showAllEntryTest());
        model.addAttribute("mode", "ALL_POSITIONS");
        return PATH;
    }


    @RequestMapping("/delete-position")
    public String deletePositiont(@RequestParam int id, Model model) {
        positionService.deleteById(id);
        model.addAttribute("entryTests", positionService.showAllEntryTest());
        model.addAttribute("mode", "ALL_POSITIONS");
        return PATH;
    }

    @RequestMapping("/edit-postion")
    public String editPosition(@RequestParam int id, Model model) {
        model.addAttribute("position", positionService.editEntryTest(id));
        model.addAttribute("mode", "MODE_UPDATE_POSITION");
        return PATH;
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public void deleteSkill(@PathVariable int id) {
        positionService.deleteById(id);
    }

    @PostMapping("/edit/{id}")
    public @ResponseBody
    void editSkill(@PathVariable int id, @RequestParam(name = "name") String name) {
        Position positionToEdit = positionService.getById(id);
        positionToEdit.setName(name);
    }

    // dataTable
    // Phuc vu cho DataTables
    @RequestMapping(value = "/get/all", produces = "application/json")
    public @ResponseBody
    String getAllPositions() {
        List<Position> positions = positionService.getAll();
        JsonObject result = new JsonObject();
        JsonArray positionJson = (JsonArray) GSON.toJsonTree(positions);
        result.add("data", positionJson);
        return result.toString();
    }

    @GetMapping(value = "/get/{id}")
    @ResponseBody
    public Position getSkillById(@PathVariable int id) {
        return positionService.getById(id);
    }

    @PostMapping(value = "/dataTable/create", produces = "application/json")
    public @ResponseBody
    String dataTableAdd(@RequestBody String data) {
        Map<String, Object> json = GSON.fromJson(data, Map.class);
        Map<String, Object> majorJson = (Map<String, Object>) json.get("major");
        Major major = dataTableService.getMajorFromJson(majorJson);
        Position position = new Position();
        position.setName(json.get("name").toString());
        position.setSalary(json.get("salary").toString());
        position.setSkills(dataTableService.getSkillsFromJson(json));
        major.addPosition(position);
        positionService.save(position);
        return dataTableService.populateJsonResult(position).toString();
    }

    @PutMapping(value = "/dataTable/edit", produces = "application/json")
    public @ResponseBody
    String dataTableEdit(@RequestBody String data) {
        Map<String, Object> json = GSON.fromJson(data, Map.class);
        Map<String, Object> majorJson = (Map<String, Object>) json.get("major");
        Major major = dataTableService.getMajorFromJson(majorJson);
        int id = ((Double) json.get("id")).intValue();
        Position position = positionService.getById(id);
        position.setName(json.get("name").toString());
        position.setSalary(json.get("salary").toString());
        position.setSkills(dataTableService.getSkillsFromJson(json));
        major.addPosition(position);
        positionService.save(position);
        return dataTableService.populateJsonResult(position).toString();
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
        positionService.deleteById(id);
        return dataTableService.populateJsonResult(null).toString();
    }

}
