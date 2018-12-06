package com.fsoft.fa.interviewprocessmanagement.service;

import com.fsoft.fa.interviewprocessmanagement.model.EntryTest;
import com.fsoft.fa.interviewprocessmanagement.repository.EntryTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EntryTestService {
    private EntryTestRepository entryTestRepository;

    @Autowired
    public void setEntryTestRepository(EntryTestRepository entryTestRepository) {
        this.entryTestRepository = entryTestRepository;
    }

    public EntryTestService() {
    }

    public void saveMyEntryTest(EntryTest entryTest) {
        entryTestRepository.save(entryTest);
    }

    public List<EntryTest> showAllEntryTest() {
        return entryTestRepository.findAll();
    }

    public void deleteMyEntryTest(int id) {
        entryTestRepository.deleteById(id);
    }

    public Optional<EntryTest> editEntryTest(int id) {
        return entryTestRepository.findById(id);
    }

    public EntryTest getEntryTestById(int id) {
        return entryTestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entry Test with id " + id + " not found"));
    }

    public EntryTest getByName(String name) {
        return entryTestRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Entry test with name " + name + " not found"));
    }
}
