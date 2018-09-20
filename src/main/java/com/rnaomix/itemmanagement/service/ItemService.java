package com.rnaomix.itemmanagement.service;

import com.rnaomix.itemmanagement.model.Item;

import java.util.List;
import java.util.Set;

public interface ItemService {

    List<Item> getItemList();

    Item saveItem(Item item);

    int deleteItem(Set<Integer> itemIds);

    Item getItem(Integer itemId);
}
