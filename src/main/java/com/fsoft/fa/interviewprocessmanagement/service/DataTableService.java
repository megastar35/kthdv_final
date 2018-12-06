package com.fsoft.fa.interviewprocessmanagement.service;

import com.fsoft.fa.interviewprocessmanagement.config.HibernateProxyTypeAdapter;
import com.fsoft.fa.interviewprocessmanagement.model.*;
import com.fsoft.fa.interviewprocessmanagement.serializer.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class DataTableService {

    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
            .registerTypeAdapter(new TypeToken<Set<Position>>() {
            }.getType(), new PositionSetSerializer())
            .registerTypeAdapter(new TypeToken<Set<Major>>() {
            }.getType(), new MajorSetSerializer())
            .registerTypeAdapter(new TypeToken<Set<Recruitment>>() {
            }.getType(), new RecruitmentSetSerializer())
            .registerTypeAdapter(new TypeToken<Set<Candidate>>() {
            }.getType(), new CandidateSetSerializer())
            .registerTypeAdapter(new TypeToken<Set<EntryTest>>() {
            }.getType(), new EntryTestSetSerializer())
            .setPrettyPrinting().create();

    private PositionService positionService;
    private MajorService majorService;
    private SkillService skillService;
    private UserDetailService userDetailService;
    private RoleService roleService;
    private EntryTestService entryTestService;
    private RecruitmentService recruitmentService;
    private UploadFileService uploadFileService;
    private CandidateService candidateService;

    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Autowired
    public void setUploadFileService(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @Autowired
    public void setEntryTestService(EntryTestService entryTestService) {
        this.entryTestService = entryTestService;
    }

    @Autowired
    public void setUserDetailService(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setRecruitmentService(RecruitmentService recruitmentService) {
        this.recruitmentService = recruitmentService;
    }

    @Autowired
    public void setSkillService(SkillService skillService) {
        this.skillService = skillService;
    }

    @Autowired
    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }

    @Autowired
    public void setMajorService(MajorService majorService) {
        this.majorService = majorService;
    }

    @Autowired
    public DataTableService() {
    }

    public JsonObject populateJsonResult(Object object) {
        JsonObject result = new JsonObject();
        JsonArray array = new JsonArray();
        array.add(GSON.toJsonTree(object));
        result.add("data", array);
        return result;
    }

    public Skill getSkillFromJson(Map<String, Object> json) {
        return skillService.getSkillByName(json.get("name").toString());
    }

    public Candidate getCandidateFromJson(Map<String, Object> json) {
        return candidateService.getCandidateByName(json.get("name").toString());
    }

    public User getUserFromJson(Map<String, Object> json) {
        return userDetailService.getByName(json.get("name").toString());
    }

    public Position getPositionFromJson(Map<String, Object> json) {
        return positionService.getByName(json.get("name").toString());
    }

    public Recruitment getRecruitmentFromJson(Map<String, Object> json) {
        return recruitmentService.getByName(json.get("name").toString());
    }

    public UploadFile getUploadFromJson(Map<String, Object> json) {
        return uploadFileService.getById(((Double) json.get("id")).intValue());
    }

    public Major getMajorFromJson(Map<String, Object> json) {
        return majorService.getByName(json.get("name").toString());
    }

    public Role getRoleFromJson(Map<String, Object> json) {
        return roleService.getByName(json.get("name").toString());
    }

    public Set<EntryTest> getEntryTestsFromJson(Map<String, Object> json) {
        Set<EntryTest> entryTests = new HashSet<>();
        for (Map<String, Object> map : ((ArrayList<Map<String, Object>>) json.get("entryTests"))) {
            String entryTestName = map.get("name").toString();
            EntryTest entryTest = entryTestService.getByName(entryTestName);
            entryTests.add(entryTest);
        }
        return entryTests;
    }

    public Set<Skill> getSkillsFromJson(Map<String, Object> json) {
        Set<Skill> skillSet = new HashSet<>();
        for (Map<String, Object> map : ((ArrayList<Map<String, Object>>) json.get("skills"))) {
            String skillName = map.get("name").toString();
            Skill skill = skillService.getSkillByName(skillName);
            skillSet.add(skill);
        }
        return skillSet;
    }

    public Set<Major> getMajorsFromJson(Map<String, Object> json) {
        Set<Major> majorSet = new HashSet<>();
        for (Map<String, Object> map : ((ArrayList<Map<String, Object>>) json.get("majors"))) {
            String majorName = map.get("name").toString();
            System.out.println(majorName);
            Major major = majorService.getByName(majorName);
            majorSet.add(major);
        }
        return majorSet;
    }

    public Set<Position> getPositionsFromJson(Map<String, Object> json) {
        Set<Position> positionSet = new HashSet<>();
        for (Map<String, Object> map : ((ArrayList<Map<String, Object>>) json.get("positions"))) {
            String positionName = map.get("name").toString();
            Position position = positionService.getByName(positionName);
            positionSet.add(position);
        }
        return positionSet;
    }

    public Set<Role> getRolesFromJson(Map<String, Object> json) {
        Set<Role> roleSet = new HashSet<>();
        for (Map<String, Object> map : ((ArrayList<Map<String, Object>>) json.get("roles"))) {
            String roleName = map.get("name").toString();
            Role role = roleService.getByName(roleName);
            roleSet.add(role);
        }
        return roleSet;
    }


    public Set<Candidate> getCandidatesFromJson(Map<String, Object> json) {
        Set<Candidate> candidateSet = new HashSet<>();
        for (Map<String, Object> map : ((ArrayList<Map<String, Object>>) json.get("candidates"))) {
            String candidateName = map.get("name").toString();
            Candidate candidate = candidateService.getCandidateByName(candidateName);
            candidateSet.add(candidate);
        }
        return candidateSet;
    }

    public Set<User> getUsersFromJson(Map<String, Object> json) {
        Set<User> userSet = new HashSet<>();
        for (Map<String, Object> map : ((ArrayList<Map<String, Object>>) json.get("interviewers"))) {
            String username = map.get("name").toString();
            User user = userDetailService.getByName(username);
            userSet.add(user);
        }
        return userSet;
    }
}
