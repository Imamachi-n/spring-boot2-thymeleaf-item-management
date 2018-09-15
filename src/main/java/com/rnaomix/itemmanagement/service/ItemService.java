package com.rnaomix.itemmanagement.service;

import com.rnaomix.itemmanagement.model.Item;

import java.util.List;
import java.util.Set;

public interface ItemService {

    public List<Item> getItemList();

    public Item saveItem(Item item);

    public int deleteItem(Set<Integer> itemIds);
}
