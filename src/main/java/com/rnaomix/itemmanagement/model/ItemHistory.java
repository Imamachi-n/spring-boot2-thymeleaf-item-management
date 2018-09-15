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
@AllArgsConstructor
public class ItemHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long orderId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemHistory")
    @OrderBy("orderDetailId ASC")
    private List<ItemHistoryDetail> itemHistoryDetails;

    @Column(nullable = false)
    private java.sql.Date createDate;

    public ItemHistory() {}

    public ItemHistory(User user, String description, List<ItemHistoryDetail> itemHistoryDetails, java.sql.Date createDate){
        this.user = user;
        this.description = description;
        this.itemHistoryDetails = itemHistoryDetails;
        this.createDate = createDate;
    }
}
