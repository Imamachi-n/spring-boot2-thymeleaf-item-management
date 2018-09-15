package com.rnaomix.itemmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "item_history_detail")
@Data
@AllArgsConstructor
public class ItemHistoryDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long orderDetailId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", nullable = false)
    private ItemHistory itemHistory;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId", nullable = false)
    private Item item;

    @Column(nullable = false)
    private long amount;

    public ItemHistoryDetail() {}

    public ItemHistoryDetail(ItemHistory itemHistory, Item item, long amount){
        this.itemHistory = itemHistory;
        this.item = item;
        this.amount = amount;
    }
}
