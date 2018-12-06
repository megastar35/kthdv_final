package com.fsoft.fa.interviewprocessmanagement.controller;

import com.fsoft.fa.interviewprocessmanagement.model.Candidate;
import com.fsoft.fa.interviewprocessmanagement.model.Interview;
import com.fsoft.fa.interviewprocessmanagement.model.User;
import com.fsoft.fa.interviewprocessmanagement.service.CandidateService;
import com.fsoft.fa.interviewprocessmanagement.service.DataTableService;
import com.fsoft.fa.interviewprocessmanagement.service.InterviewService;
import com.fsoft.fa.interviewprocessmanagement.service.UserDetailService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.fsoft.fa.interviewprocessmanagement.service.DataTableService.GSON;

@Controller
@RequestMapping("/interview")
public class InterviewController {

    private InterviewService interviewService;
    private DataTableService dataTableService;
    private CandidateService candidateService;
    private UserDetailService userDetailService;

    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Autowired
    public void setUserDetailService(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Autowired
    public void setInterviewService(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @Autowired
    public void setDataTableService(DataTableService dataTableService) {
        this.dataTableService = dataTableService;
    }

    // Phuc vu cho DataTables
    @GetMapping(value = "/add-interview", produces = "application/json")
    @ResponseBody
    public String addInterview(@RequestParam(name = "name", defaultValue = "Java") int id) {
        Interview newInterview = new Interview();
        newInterview.setId(id);
        interviewService.saveInterview(newInterview);
        return GSON.toJsonTree(newInterview).toString();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public void deleteEntryTest(@PathVariable int id) {
        interviewService.deleteAnInterview(id);
    }

    @PostMapping("/edit/{id}")
    public @ResponseBody
    void editInterview(@PathVariable int id, @RequestParam(name = "name") double interviewScore) {
        Interview interviewToEdit = interviewService.getById(id);
        interviewToEdit.setInterviewScore(interviewScore);
    }

    @RequestMapping(value = "/get/all", produces = "application/json")
    public @ResponseBody
    String getAllInterviews() {
        List<Interview> interviews = interviewService.showAllInterviews();
        JsonObject result = new JsonObject();
        JsonArray interviewJson = (JsonArray) GSON.toJsonTree(interviews);
        result.add("data", interviewJson);
        return result.toString();
    }

    @PostMapping(value = "/dataTable/create", produces = "application/json")
    public @ResponseBody
    String dataTableAdd(@RequestBody String data) {
        Map<String, Object> json = GSON.fromJson(data, Map.class);
        Map<String, Object> candidateJson = (Map<String, Object>) json.get("candidate");
        Map<String, Object> interviewerJson = (Map<String, Object>) json.get("interviewer");
        Candidate candidate = dataTableService.getCandidateFromJson(candidateJson);
        User interviewer = dataTableService.getUserFromJson(interviewerJson);
        Interview interview = new Interview();
        interview.setInterviewDate(LocalDate.parse((json.get("interviewDate").toString())));
        interview.setDescription(json.get("description").toString());
        candidate.enrollInterview(interview);
        interviewer.enrollInterview(interview);
        interview.setInterviewScore(((Double) Double.parseDouble(json.get("interviewScore").toString())).intValue());

        interviewService.saveInterview(interview);
        return dataTableService.populateJsonResult(interview).toString();
    }

    @PutMapping(value = "/dataTable/edit", produces = "application/json")
    public @ResponseBody
    String dataTableEdit(@RequestBody String data) {
        System.out.println(data);
        Map<String, Object> json = GSON.fromJson(data, Map.class);
        Map<String, Object> candidateJson = (Map<String, Object>) json.get("candidate");
        Map<String, Object> interviewerJson = (Map<String, Object>) json.get("interviewer");

        Candidate candidate = dataTableService.getCandidateFromJson(candidateJson);
        User interviewer = dataTableService.getUserFromJson(interviewerJson);

        int id = ((Double) json.get("id")).intValue();
        Interview interview = interviewService.getById(id);

        interview.setInterviewDate(LocalDate.parse((json.get("interviewDate").toString())));
        interview.setDescription(json.get("description").toString());
        candidate.enrollInterview(interview);
        interviewer.enrollInterview(interview);
        interview.setInterviewScore(((Double) Double.parseDouble(json.get("interviewScore").toString())).intValue());

        interviewService.saveInterview(interview);
        return dataTableService.populateJsonResult(interview).toString();
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
        interviewService.deleteAnInterview(id);
        return dataTableService.populateJsonResult(null).toString();
    }
}
