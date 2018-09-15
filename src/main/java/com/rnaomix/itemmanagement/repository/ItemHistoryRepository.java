package com.rnaomix.itemmanagement.repository;

import com.rnaomix.itemmanagement.model.ItemHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemHistoryRepository extends JpaRepository<ItemHistory, Long> {
    // TodoRepository#findAll
    // TodoRepository#findById
    // TodoRepository#save
    // TodoRepository#deleteById
}
