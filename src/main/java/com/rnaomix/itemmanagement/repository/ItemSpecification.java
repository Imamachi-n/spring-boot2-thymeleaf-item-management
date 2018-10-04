package com.rnaomix.itemmanagement.repository;

import com.rnaomix.itemmanagement.model.Item;
import org.springframework.data.jpa.domain.Specification;

public class ItemSpecification {

    // Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cd
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

    public static Specification<Item> keyword(String keyword){
        return keyword == null || keyword.isEmpty() ? null
                : (root, query, cb) -> cb.or(
                cb.like(root.<String>get("itemName"), "%" + keyword + "%"),
                cb.equal(root.<String>get("catId"), keyword)
        );
    }
}
