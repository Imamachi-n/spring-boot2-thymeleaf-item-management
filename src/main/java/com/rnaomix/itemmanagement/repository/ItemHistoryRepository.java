package com.rnaomix.itemmanagement.repository;

import com.rnaomix.itemmanagement.model.ItemHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemHistoryRepository extends JpaRepository<ItemHistory, Long> {
    // TodoRepository#findAll
    // TodoRepository#findById
    // TodoRepository#save
    // TodoRepository#deleteById

    @Query("select h from ItemHistory h where year(h.createDate) = ?1 and month(h.createDate) = ?2")
    List<ItemHistory> findByYearAndMonth(int year, int month);
}
