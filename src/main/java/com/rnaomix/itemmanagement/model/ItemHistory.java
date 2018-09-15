package com.rnaomix.itemmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "item_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long orderId;

//    @Column(nullable = false)
//    private long user;

    @Column(nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemHistory")
    @OrderBy("orderDetailId ASC")
    private List<ItemHistoryDetail> itemHistoryDetails;

}
