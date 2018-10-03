package com.rnaomix.itemmanagement.repository;

import com.rnaomix.itemmanagement.model.Item;
import org.springframework.data.jpa.domain.Specification;

public class ItemSpecification {
    public static Specification<Item> catId(String catId){
        return catId == null || catId.isEmpty() ? null
                : (root, query, cb) -> cb.equal(
                        root.<String>get("catId"), catId
                  );
    }

    public static Specification<Item> itemName(String itemName){
        return itemName == null || itemName.isEmpty() ? null
                : (root, query, cb) -> cb.like(
                        root.<String>get("itemName"),
                        "%" + itemName + "%"
                  );
    }
}
