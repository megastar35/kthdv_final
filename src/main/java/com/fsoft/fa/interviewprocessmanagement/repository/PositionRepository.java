package com.fsoft.fa.interviewprocessmanagement.repository;

import com.fsoft.fa.interviewprocessmanagement.model.Position;
import com.fsoft.fa.interviewprocessmanagement.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

    Optional<Position> findByName(String name);

    List<Position> getByMajor_Id(int majorId);

    List<Position> getByMajor_Name(String majorName);

    void deleteByMajor_Id(int id);
}
