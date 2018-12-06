package com.fsoft.fa.interviewprocessmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsoft.fa.interviewprocessmanagement.model.Role;
import com.fsoft.fa.interviewprocessmanagement.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
    Optional<User> findByUsername(String username);

    List<User> getRolesByName(String name);
}
