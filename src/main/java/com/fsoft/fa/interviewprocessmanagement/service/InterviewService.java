package com.fsoft.fa.interviewprocessmanagement.service;

import com.fsoft.fa.interviewprocessmanagement.model.Interview;
import com.fsoft.fa.interviewprocessmanagement.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterviewService {
    private InterviewRepository repository;

    @Autowired
    public void setRepository(InterviewRepository repository) {
        this.repository = repository;
    }

    public void saveInterview(Interview interview) {
        repository.save(interview);
    }

    public void deleteAnInterview(int interviewId) {
        repository.deleteById(interviewId);
    }

    public List<Interview> showAllInterviews() {
        return repository.findAll();
    }

    public Interview getById(int id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("Interview with id " + id + " not found")
        );
    }
}
