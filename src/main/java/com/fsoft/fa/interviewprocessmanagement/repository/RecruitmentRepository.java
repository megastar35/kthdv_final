package com.fsoft.fa.interviewprocessmanagement.repository;

import com.fsoft.fa.interviewprocessmanagement.model.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Integer> {
    Optional<Recruitment> findByName(String name);
}
