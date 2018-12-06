package com.fsoft.fa.interviewprocessmanagement.controller;

import com.fsoft.fa.interviewprocessmanagement.model.Skill;
import com.fsoft.fa.interviewprocessmanagement.service.DataTableService;
import com.fsoft.fa.interviewprocessmanagement.service.MajorService;
import com.fsoft.fa.interviewprocessmanagement.service.SkillService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.fsoft.fa.interviewprocessmanagement.service.DataTableService.GSON;

@RequestMapping("/skill")
@Controller
public class SkillController {

    private SkillService skillService;
    private DataTableService dataTableService;
    private MajorService majorService;

    @Autowired
    public void setMajorService(MajorService majorService) {
        this.majorService = majorService;
    }

    @Autowired
    public void setSkillService(SkillService skillService) {
        this.skillService = skillService;
    }

    @Autowired
    public void setDataTableService(DataTableService dataTableService) {
        this.dataTableService = dataTableService;
    }

    @GetMapping("/show-skill")
    public String showAllUsers(Model model) {
        model.addAttribute("skill", skillService.getAllSkills());
        model.addAttribute("mode", "ALL_SKILL");
        return "management_skill";
    }

    @PostMapping("/delete-skill")
    public String deleteUser(@RequestParam int id, HttpServletRequest request) {
        skillService.deleteById(id);
        request.setAttribute("skill", skillService.getAllSkills());
        request.setAttribute("mode", "ALL_SKILL");
        return "management_skill";
    }

    @PostMapping("/add-skill")
    public String addSkill(@ModelAttribute Skill skill, HttpServletRequest request) {
        skillService.save(skill);
        request.setAttribute("mode", "ALL_SKILL");
        return "management_skill";
    }

    // Phuc vu cho DataTables
    @GetMapping(value = "/add-skill", produces = "application/json")
    @ResponseBody
    public String addSkill(@RequestParam(name = "name", defaultValue = "Java") String name) {
        Skill newSkill = new Skill();
        newSkill.setName(name);
        skillService.save(newSkill);
        return GSON.toJsonTree(newSkill).toString();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public void deleteSkill(@PathVariable int id) {
        skillService.deleteById(id);
    }

    @PostMapping("/edit/{id}")
    public @ResponseBody
    void editSkill(@PathVariable int id, @RequestParam(name = "name") String name) {
        Skill skillToEdit = skillService.getSkillById(id);
        skillToEdit.setName(name);
    }

    @RequestMapping(value = "/get/all", produces = "application/json")
    public @ResponseBody
    String getAllSkills() {
        List<Skill> skills = skillService.getAllSkills();
        JsonObject result = new JsonObject();
        result.add("data", GSON.toJsonTree(skills));
        return result.toString();
    }

    @PostMapping(value = "/dataTable/create", produces = "application/json")
    public @ResponseBody
    String dataTableAdd(@RequestBody String data) {
        Map<String, Object> json = GSON.fromJson(data, Map.class);
        System.out.println(json.get("name"));
        Skill skill = new Skill();
        skill.setName(json.get("name").toString());
        skill.setDescription(json.get("description").toString());
        skillService.save(skill);
        return dataTableService.populateJsonResult(skill).toString();
    }

    @PutMapping(value = "/dataTable/edit", produces = "application/json")
    public @ResponseBody
    String dataTableEdit(@RequestBody String data) {
        System.out.println(data);
        Map<String, Object> json = GSON.fromJson(data, Map.class);
        int id = ((Double) json.get("id")).intValue();
        String name = json.get("name").toString();
        Skill skill = skillService.getSkillById(id);
        skill.setName(name);
        skill.setDescription(json.get("description").toString());
        skillService.save(skill);
        return dataTableService.populateJsonResult(skill).toString();
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
        skillService.deleteById(id);
        return dataTableService.populateJsonResult(null).toString();
    }

    @GetMapping(value = "/get/{id}")
    @ResponseBody
    public Skill getSkillById(@PathVariable int id) {
        return skillService.getSkillById(id);
    }

}
