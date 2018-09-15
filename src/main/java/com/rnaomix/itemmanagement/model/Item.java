package com.rnaomix.itemmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "item")
@Data
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long itemId;

    @Column(nullable = false, unique = true)
    private String catId;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private long price;

    public Item() {}

    public Item(String catId, String itemName, long price){
        this.catId = catId;
        this.itemName = itemName;
        this.price = price;
    }
}
