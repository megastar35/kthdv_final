package com.fsoft.fa.interviewprocessmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import com.fsoft.fa.interviewprocessmanagement.model.Role;
import com.fsoft.fa.interviewprocessmanagement.repository.RoleRepository;

@Service
public class RoleService {

	private RoleRepository roleRepository;

    @Autowired
    public void setMajorRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void findByName(String name) {
    	roleRepository.findByName(name);
    }

    public void save(Role major) {
    	roleRepository.save(major);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void deleteById(int id) {
    	roleRepository.deleteById(id);
    }

    public void addRole(Role major) {
    	roleRepository.save(major);
    }

    public Role getRoleById(int id) {
        return roleRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Role with id " + id + " is not exist"));
    }

    public Role getByName(String name) {
        return roleRepository.findByName(name).orElseThrow(
                () -> new NotFoundException("Role with name " + name + " is not exist"));
    }
}
