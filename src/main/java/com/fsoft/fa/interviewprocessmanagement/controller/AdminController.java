package com.fsoft.fa.interviewprocessmanagement.controller;

import com.fsoft.fa.interviewprocessmanagement.model.User;
import com.fsoft.fa.interviewprocessmanagement.service.DataTableService;
import com.fsoft.fa.interviewprocessmanagement.service.UserDetailService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import static com.fsoft.fa.interviewprocessmanagement.service.DataTableService.GSON;

@Controller
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    private UserDetailService userDetailService;
    private DataTableService dataTableService;

    @Autowired
    public void setUserService(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Autowired
    public void setDataTableService(DataTableService dataTableService) {
        this.dataTableService = dataTableService;
    }

    @RequestMapping
    public String showAdmin(ModelMap model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username", name);
        return "admin";
    }

    // Phuc vu cho DataTables
    @GetMapping(value = "/add-user", produces = "application/json")
    @ResponseBody
    public String addUser(@RequestParam(name = "name", defaultValue = "Java") String name) {
        User newUser = new User();
        newUser.setName(name);
        userDetailService.saveMyUser(newUser);
        return GSON.toJsonTree(newUser).toString();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable int id) {
        userDetailService.deleteById(id);
    }

    @PostMapping("/edit/{id}")
    public @ResponseBody
    void editUser(@PathVariable int id, @RequestParam(name = "name") String name) {
        User userToEdit = userDetailService.getById(id);
        userToEdit.setName(name);
    }

    @RequestMapping(value = "/get/all", produces = "application/json")
    public @ResponseBody
    String getAllUsers() {
        List<User> users = userDetailService.showAllUsers();
        JsonObject result = new JsonObject();
        JsonArray userJson = (JsonArray) GSON.toJsonTree(users);
        result.add("data", userJson);
        return result.toString();
    }


    @PostMapping(value = "/dataTable/create", produces = "application/json")
    public @ResponseBody
    String dataTableAdd(@RequestBody String data) {
        Map<String, Object> json = GSON.fromJson(data, Map.class);
        System.out.println(json.get("roleName"));
        User user = new User();
        user.setName(json.get("name").toString());
        user.setEmail(json.get("email").toString());
        user.setUsername(json.get("username").toString());
        user.setPassword(json.get("password").toString());
        user.setPhone(((Double) Double.parseDouble(json.get("phone").toString())).intValue());
        user.setRoles(dataTableService.getRolesFromJson(json));
        user.setActive(Boolean.valueOf(json.get("isActive").toString()));
        userDetailService.saveMyUser(user);
        return dataTableService.populateJsonResult(user).toString();
    }

    @PutMapping(value = "/dataTable/edit", produces = "application/json")
    public @ResponseBody
    String dataTableEdit(@RequestBody String data) {
        System.out.println(data);
        Map<String, Object> json = GSON.fromJson(data, Map.class);
        int id = ((Double) json.get("id")).intValue();
        String name = json.get("name").toString();
        User user = userDetailService.getById(id);
        user.setName(name);
        user.setEmail(json.get("email").toString());
        user.setUsername(json.get("username").toString());
        user.setPassword(json.get("password").toString());
        user.setPhone(((Double) Double.parseDouble(json.get("phone").toString())).intValue());
        user.setActive(Boolean.valueOf(json.get("isActive").toString()));
        user.setRoles(dataTableService.getRolesFromJson(json));
        userDetailService.saveMyUser(user);
        return dataTableService.populateJsonResult(user).toString();
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
        userDetailService.deleteById(id);
        return dataTableService.populateJsonResult(null).toString();
    }

    @GetMapping(value = "/get/current")
    @ResponseBody
    public User getUserById(Principal principal) {
    	String name = principal.getName();
        return userDetailService.getByName(name);
    }
    

}
