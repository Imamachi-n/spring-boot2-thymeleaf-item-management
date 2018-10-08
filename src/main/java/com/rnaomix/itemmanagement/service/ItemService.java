package com.rnaomix.itemmanagement.service;

import com.rnaomix.itemmanagement.model.Item;

import java.util.List;
import java.util.Set;

public interface ItemService {

    List<Item> getItemList();

    Item saveItem(Item item);

    int deleteItem(List<Integer> itemIds);

    Item getItem(Integer itemId);

    List<Item> searchItemList(String searchItems);
}
