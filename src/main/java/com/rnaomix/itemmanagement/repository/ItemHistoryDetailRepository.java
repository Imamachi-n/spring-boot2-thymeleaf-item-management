package com.rnaomix.itemmanagement.repository;

import com.rnaomix.itemmanagement.model.ItemHistoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemHistoryDetailRepository extends JpaRepository<ItemHistoryDetail, Long> {
    // TodoRepository#findAll
    // TodoRepository#findById
    // TodoRepository#save
    // TodoRepository#deleteById
}
