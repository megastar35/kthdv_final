package com.fsoft.fa.interviewprocessmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsoft.fa.interviewprocessmanagement.model.Role;
import com.fsoft.fa.interviewprocessmanagement.service.RoleService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import static com.fsoft.fa.interviewprocessmanagement.service.DataTableService.GSON;


@Controller
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;


    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }


    @RequestMapping(value = "/get/all", produces = "application/json")
    public @ResponseBody
    String getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        JsonObject result = new JsonObject();
        result.add("data", GSON.toJsonTree(roles));
        return result.toString();
    }
}
