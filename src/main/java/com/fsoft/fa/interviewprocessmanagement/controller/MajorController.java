package com.fsoft.fa.interviewprocessmanagement.controller;

import com.fsoft.fa.interviewprocessmanagement.model.Major;
import com.fsoft.fa.interviewprocessmanagement.service.DataTableService;
import com.fsoft.fa.interviewprocessmanagement.service.MajorService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.fsoft.fa.interviewprocessmanagement.service.DataTableService.GSON;

@RequestMapping("/major")
@Controller
public class MajorController {

    private final String PATH = "entry_test";
    private MajorService majorService;
    private DataTableService dataTableService;

    @Autowired
    public void setDataTableService(DataTableService dataTableService) {
        this.dataTableService = dataTableService;
    }

    @Autowired
    public void setMajorService(MajorService majorService) {
        this.majorService = majorService;
    }

    @RequestMapping
    public String showMajor() {
        return PATH;
    }

//	@RequestMapping("/create-major")
//	public String createMajor(Model model) {
//		model.addAttribute("mode", "MODE_CREATE_MAJOR");
//		return PATH;
//	}

//	@PostMapping("/save-major")
//	public String registerUser(@ModelAttribute Major major,Model model ) {
//		majorService.save(major);
//		model.addAttribute("mode", "MODE_HOME");
//		return PATH;
//	}

    @GetMapping("/show-major")
    public String showAllMajor(Model model) {
        model.addAttribute("major", majorService.getAll());
        model.addAttribute("mode", "ALL_MAJOR");
        return PATH;

    }

    @RequestMapping("/delete-major")
    public String deleteMajor(@RequestParam int id, Model model) {
        majorService.deleteById(id);
        model.addAttribute("major", majorService.getAll());
        model.addAttribute("mode", "ALL_MAJOR");
        return PATH;
    }

    @PostMapping("/add-major")
    public String addMajor(@ModelAttribute Major major, Model model) {
        majorService.addMajor(major);
        model.addAttribute("mode", "MODE_HOME");
        return PATH;
    }

//    @RequestMapping("/edit-major")
//    public String editMajor(@RequestParam int id, Model model) {
//        model.addAttribute("major", majorService.getMajor(id));
//        model.addAttribute("mode", "MODE_UPDATE_MAJOR");
//        return PATH;
//    }

    @RequestMapping(value = "/get/all", produces = "application/json")
    public @ResponseBody
    String getAllMajors() {
        List<Major> majors = majorService.getAll();
        JsonObject result = new JsonObject();
        JsonArray majorJson = (JsonArray) GSON.toJsonTree(majors);
        result.add("data", majorJson);
        return result.toString();
    }

    @PostMapping(value = "/dataTable/create", produces = "application/json")
    public @ResponseBody
    String dataTableAdd(@RequestBody String data) {
        Map<String, Object> json = GSON.fromJson(data, Map.class);
        Major major = new Major();
        major.setName(json.get("name").toString());
        major.setDescription(json.get("description").toString());
        majorService.save(major);
        return dataTableService.populateJsonResult(major).toString();
    }

    @PutMapping(value = "/dataTable/edit", produces = "application/json")
    public @ResponseBody
    String dataTableEdit(@RequestBody String data) {
        Map<String, Object> json = GSON.fromJson(data, Map.class);
        int id = ((Double) json.get("id")).intValue();
        Major major = majorService.getMajor(id);
        major.setName(json.get("name").toString());
        major.setDescription(json.get("description").toString());
        majorService.save(major);
        return dataTableService.populateJsonResult(major).toString();
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
        Map<String, Object> json = GSON.fromJson(data, Map.class);
        int id = ((Double) json.get("id")).intValue();
        System.out.println(id);
        majorService.deleteById(id);
        return dataTableService.populateJsonResult(null).toString();
    }
}
