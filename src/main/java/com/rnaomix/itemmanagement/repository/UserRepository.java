package com.rnaomix.itemmanagement.repository;

import com.rnaomix.itemmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // TodoRepository#findAll
    // TodoRepository#findById
    // TodoRepository#save
    // TodoRepository#deleteById
}
