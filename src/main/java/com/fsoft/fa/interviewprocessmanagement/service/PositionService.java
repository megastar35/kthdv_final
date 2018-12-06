package com.fsoft.fa.interviewprocessmanagement.service;

import com.fsoft.fa.interviewprocessmanagement.model.Position;
import com.fsoft.fa.interviewprocessmanagement.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    private PositionRepository repository;

    @Autowired
    public void setRepository(PositionRepository repository) {
        this.repository = repository;
    }

    public List<Position> getAll() {
        return repository.findAll();
    }

    public Position getById(int id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("Position with id " + id + " not found"));
    }

    public Position getByName(String positionName) {
        return repository.findByName(positionName).orElseThrow(() ->
                new NotFoundException("Position with name " + positionName + " not found"));
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public void save(Position position) {
        repository.save(position);
    }

    public Object showAllEntryTest() {
        return repository.findAll();
    }

    public Object editEntryTest(int id) {
        return repository.findById(id);
    }

    public List<Position> getByMajorId(int id) {
        return repository.getByMajor_Id(id);
    }

    public List<Position> getByMajorName(String majorName) {
        return repository.getByMajor_Name(majorName);
    }

    public void deleteByMajorId(int id) {
        repository.deleteByMajor_Id(id);
    }
}
