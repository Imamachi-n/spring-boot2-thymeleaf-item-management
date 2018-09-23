package com.rnaomix.itemmanagement.repository;

import com.rnaomix.itemmanagement.model.ItemHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemHistoryRepository extends JpaRepository<ItemHistory, Long> {
    // TodoRepository#findAll
    // TodoRepository#findById
    // TodoRepository#save
    // TodoRepository#deleteById

//    @Query("select i from ItemHistory where ")
//    ItemHistory findByCreateDate
}
