package com.fsoft.fa.interviewprocessmanagement.service;

import com.fsoft.fa.interviewprocessmanagement.model.UploadFile;
import com.fsoft.fa.interviewprocessmanagement.repository.UploadFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadFileService {
    private UploadFileRepository repository;

    @Autowired
    public void setRepository(UploadFileRepository repository) {
        this.repository = repository;
    }

    public void save(UploadFile response) {
        repository.save(response);
    }

    public UploadFile getById(int id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("Image co id " + id + " khong ton tai"));
    }

    public List<UploadFile> getAll() {
        return repository.findAll();
    }
}
