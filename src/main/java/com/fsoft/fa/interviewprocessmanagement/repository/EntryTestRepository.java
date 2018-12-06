package com.fsoft.fa.interviewprocessmanagement.repository;

import com.fsoft.fa.interviewprocessmanagement.model.EntryTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntryTestRepository extends JpaRepository<EntryTest, Integer> {
    Optional<EntryTest> findByName(String name);
}
