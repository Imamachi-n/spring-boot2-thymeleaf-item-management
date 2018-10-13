package com.rnaomix.itemmanagement.repository;

import com.rnaomix.itemmanagement.model.ItemHistoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemHistoryDetailRepository extends JpaRepository<ItemHistoryDetail, Long> {
    // TodoRepository#findAll
    // TodoRepository#findById
    // TodoRepository#save
    // TodoRepository#deleteById

    List<ItemHistoryDetail> findFirst9ByOrderByOrderDetailIdDesc();
}
