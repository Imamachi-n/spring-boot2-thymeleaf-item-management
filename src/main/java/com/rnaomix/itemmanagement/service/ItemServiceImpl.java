package com.rnaomix.itemmanagement.service;

import com.rnaomix.itemmanagement.model.Item;
import com.rnaomix.itemmanagement.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getItemList(){
        return itemRepository.findAll();
    }

    @Override
    @Transactional
    public Item saveItem(Item item){
        return itemRepository.save(item);
    }

    @Override
    public int deleteItem(Set<Integer> itemIds){
        return itemRepository.deleteItemById(itemIds);
    }
}