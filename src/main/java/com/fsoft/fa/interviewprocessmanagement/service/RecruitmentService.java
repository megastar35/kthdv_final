package com.fsoft.fa.interviewprocessmanagement.service;

import com.fsoft.fa.interviewprocessmanagement.model.Recruitment;
import com.fsoft.fa.interviewprocessmanagement.repository.RecruitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecruitmentService {
    private RecruitmentRepository repository;
    private CandidateService candidateService;

    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Autowired
    public void setRepository(RecruitmentRepository repository) {
        this.repository = repository;
    }

    public List<Recruitment> getAll() {
        return repository.findAll();
    }

    public Recruitment getById(int id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("Recruitment with id " + id + " not found"));
    }

    public void save(Recruitment recruitment) {
        repository.save(recruitment);
    }

    @Transactional
    public void deleteById(int id) {
        candidateService.deleteByRecruitmentId(id);
        repository.deleteById(id);
    }

    public Recruitment getByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new NotFoundException("Khong tim thay mua tuyen dung " + name));
    }
}
