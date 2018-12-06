package com.fsoft.fa.interviewprocessmanagement.controller;

import com.fsoft.fa.interviewprocessmanagement.model.Candidate;
import com.fsoft.fa.interviewprocessmanagement.model.Position;
import com.fsoft.fa.interviewprocessmanagement.model.Recruitment;
import com.fsoft.fa.interviewprocessmanagement.service.CandidateService;
import com.fsoft.fa.interviewprocessmanagement.service.PositionService;
import com.fsoft.fa.interviewprocessmanagement.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Controller
public class MainController {
    private RecruitmentService recruimentSv;
    private PositionService positionSv;
    private CandidateService candidateService;

    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Autowired
    public void setPositionSv(PositionService positionSv) {
        this.positionSv = positionSv;
    }


    @Autowired
    public void setRecruimentSv(RecruitmentService recruimentSv) {
        this.recruimentSv = recruimentSv;
    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String root() {
        return "redirect:/home";
    }

    @RequestMapping(value = "/chitiet", produces = "application/json")
    public String getAllChitiet(@RequestParam(name = "id", required = true) int id, Model model) {
        Recruitment recruitment = recruimentSv.getById(id);
        Comparator<Position> comparator = Comparator
                .comparing((Position position) -> Double.parseDouble(position.getSalary()));
        Position minSalary = Collections.min(recruitment.getPositions(), comparator);
        Position maxSalary = Collections.max(recruitment.getPositions(), comparator);
        model.addAttribute("recruitment", recruitment);
        model.addAttribute("minSalary", minSalary.getSalary());
        model.addAttribute("maxSalary", maxSalary.getSalary());
        return "chitiet";
    }

    @RequestMapping(value = {"/home", "/login"}, method = RequestMethod.GET)
    public String showHome(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof AnonymousAuthenticationToken) {
            List<Recruitment> recruitments = recruimentSv.getAll();
            model.addAttribute("recruitments", recruitments);
            return "home";
        }

        return "redirect:/management_portal";
    }

    /*@RequestMapping(value = { "/home", "/login" }, method = RequestMethod.GET)
    public String showHome(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            List<Recruitment> recruitments = recruimentSv.getAll();
            model.addAttribute("recruitments", recruitments);
            return "home";
        }
        return "redirect:/management_portal";
    }
*/
    @RequestMapping(value = {"/management_portal"}, method = RequestMethod.GET)
    public String showPortal(ModelMap model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username", name);
        return "management_portal";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_HR', 'ROLE_INTERVIEWER')")
    @RequestMapping("/management_candidates")
    public String showCandidates(ModelMap model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username", name);
        return "management_candidates";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_HR')")
    @RequestMapping("/management_majors")
    public String showMajors(ModelMap model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username", name);
        return "management_majors";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_HR')")
    @RequestMapping("/management_positions")
    public String showPositions(ModelMap model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username", name);
        return "management_positions";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_HR', 'ROLE_INTERVIEWER')")
    @RequestMapping("/management_potentials")
    public String showPotentials(ModelMap model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username", name);
        return "management_potentials";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_HR')")
    @RequestMapping("/management_recruimentProcess")
    public String showRecruimentProcess(ModelMap model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username", name);
        return "management_recruimentProcess";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_HR')")
    @RequestMapping("/management_skills")
    public String showSkills(ModelMap model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username", name);
        return "management_skills";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_HR')")
    @RequestMapping("/management_entryTests")
    public String showEntryTests(ModelMap model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username", name);
        return "management_entryTests";
    }


    @PreAuthorize("hasAnyAuthority('ROLE_INTERVIEWER')")
    @RequestMapping("/management_interviews")
    public String showInterviews(ModelMap model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username", name);
        return "management_interviews";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_HR', 'ROLE_INTERVIEWER')")
    @GetMapping(value = "/candidate_detail/{id}")
    public String candidateDetail(@PathVariable int id, ModelMap model, Principal principal) {
        String name = principal.getName();
        Candidate candidate = candidateService.getById(id);
        model.addAttribute("candidate", candidate);
        model.addAttribute("username", name);
        return "candidate_detail";
    }
}



