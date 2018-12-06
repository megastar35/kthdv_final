package com.fsoft.fa.interviewprocessmanagement.service;

import com.fsoft.fa.interviewprocessmanagement.model.Skill;
import com.fsoft.fa.interviewprocessmanagement.repository.PositionRepository;
import com.fsoft.fa.interviewprocessmanagement.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {
    private SkillRepository skillRepository;
    private PositionRepository positionRepository;

    @Autowired
    public void setPositionRepository(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Autowired
    public void setRepository(SkillRepository repository) {
        this.skillRepository = repository;
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill getSkillById(int id) {
        return skillRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Skill with id " + id + " not found")
        );
    }

    public Skill getSkillByName(String name) {
        return skillRepository.findByName(name).orElseThrow(() ->
                new NotFoundException("Skill with name " + name + " not found")
        );
    }

    public void save(Skill skill) {
        skillRepository.save(skill);
    }

    public void deleteById(int id) {
        skillRepository.deleteById(id);
    }
}
