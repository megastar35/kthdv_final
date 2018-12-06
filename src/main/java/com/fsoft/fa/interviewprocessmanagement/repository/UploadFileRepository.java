package com.fsoft.fa.interviewprocessmanagement.repository;

import com.fsoft.fa.interviewprocessmanagement.model.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadFileRepository extends JpaRepository<UploadFile, Integer> {

}
