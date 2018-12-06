package com.fsoft.fa.interviewprocessmanagement.service;

import com.fsoft.fa.interviewprocessmanagement.model.Major;
import com.fsoft.fa.interviewprocessmanagement.model.Position;
import com.fsoft.fa.interviewprocessmanagement.model.Skill;
import com.fsoft.fa.interviewprocessmanagement.repository.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MajorService {

    private MajorRepository majorRepository;
    private PositionService positionService;
    private SkillService skillService;

    @Autowired
    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }

    @Autowired
    public void setSkillService(SkillService skillService) {
        this.skillService = skillService;
    }

    @Autowired
    public void setMajorRepository(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    @Autowired
    public MajorService(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    public void findByName(String name) {
        majorRepository.findByName(name);
    }

    public void save(Major major) {
        majorRepository.save(major);
    }

    public List<Major> getAll() {
        return majorRepository.findAll();
    }

    @Transactional
    public void deleteById(int id) {
        positionService.deleteByMajorId(id);
        majorRepository.deleteById(id);
    }

    public void addMajor(Major major) {
        majorRepository.save(major);
    }

    public Major getMajor(int id) {
        return majorRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Major with id " + id + " is not exist"));
    }

    public Major getByName(String name) {
        return majorRepository.findByName(name).orElseThrow(
                () -> new NotFoundException("Major with name " + name + " is not exist"));
    }

}
