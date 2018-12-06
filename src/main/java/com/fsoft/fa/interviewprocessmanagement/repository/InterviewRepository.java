package com.fsoft.fa.interviewprocessmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsoft.fa.interviewprocessmanagement.model.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer> {
}
