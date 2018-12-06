package com.fsoft.fa.interviewprocessmanagement.service;

import com.fsoft.fa.interviewprocessmanagement.model.Candidate;
import com.fsoft.fa.interviewprocessmanagement.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {
    private CandidateRepository repository;

    @Autowired
    public void setRepository(CandidateRepository repository) {
        this.repository = repository;
    }

    public void deleteByRecruitmentId(int id) {
        repository.deleteByRecruitment_Id(id);
    }

    public List<Candidate> getPotentialCandidates() {
        return repository.findByPotential(true);
    }

    /***
     * @see CandidateRepository#save(Object)
     */
    public void save(Candidate candidate) {
        repository.save(candidate);
    }


    public Candidate getById(int id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("Khong tim thay Candidate voi id " + id));
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    /***
     * @see CandidateRepository#findAll()
     */
    public List<Candidate> getAllCandidates() {
        return repository.findAll();
    }

    /***
     * @see CandidateRepository#findByRecruitment_Id(int)
     */
    public List<Candidate> getCandidatesByRecruitment(int recruitmentId) {
        return repository.findByRecruitment_Id(recruitmentId);
    }

    /***
     * @see CandidateRepository#findByPositions_IdAndRecruitment_Id(int, int)
     */
    public List<Candidate> getCandidatesByPositionAndRecruitment(int positionId, int recruitmentId) {
        return repository.findByPositions_IdAndRecruitment_Id(positionId, recruitmentId);
    }

    /***
     * @see CandidateRepository#findBySkills_IdAndRecruitment_Id(int, int)
     */
    public List<Candidate> getCandidatesBySkillAndRecruitment(int skillId, int recruitmentId) {
        return repository.findBySkills_IdAndRecruitment_Id(skillId, recruitmentId);
    }

    public Candidate getCandidateByName(String candidateName) {
        return repository.findOneByName(candidateName).orElseThrow(() ->
                new NotFoundException("Khong tim thay thi sinh " + candidateName));
    }
}
