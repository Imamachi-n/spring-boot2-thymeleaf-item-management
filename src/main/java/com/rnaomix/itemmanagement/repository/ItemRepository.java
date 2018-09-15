package com.rnaomix.itemmanagement.repository;

import com.rnaomix.itemmanagement.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // TodoRepository#findAll
    // TodoRepository#findById
    // TodoRepository#save
    // TodoRepository#deleteById
}
