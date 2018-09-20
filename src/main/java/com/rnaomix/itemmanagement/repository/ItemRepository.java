package com.rnaomix.itemmanagement.repository;

import com.rnaomix.itemmanagement.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item> {

    // TodoRepository#findAll
    // TodoRepository#findById
    // TodoRepository#save
    // TodoRepository#deleteById

    // Bulk delete
    // should be Entity name NOT table name
    @Modifying
    @Query("DELETE FROM Item WHERE itemId IN (:itemIds)")
    int deleteItemById(@Param("itemIds") Set<Integer> itemIds);

    Item findByItemId(Integer itemId);
}
