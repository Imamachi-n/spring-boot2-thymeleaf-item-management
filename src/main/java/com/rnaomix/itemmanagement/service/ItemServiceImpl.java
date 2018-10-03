package com.rnaomix.itemmanagement.service;

import com.rnaomix.itemmanagement.model.Item;
import com.rnaomix.itemmanagement.repository.ItemRepository;
import com.rnaomix.itemmanagement.repository.ItemSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
    public int deleteItem(List<Integer> itemIds){
        return itemRepository.deleteItemById(itemIds);
    }

    @Override
    public Item getItem(Integer itemId){
        return itemRepository.findByItemId(itemId);
    }

    @Override
    public List<Item> searchItemList(Item item){
        return itemRepository.findAll(Specification
                .where(ItemSpecification.catId(item.getCatId()))
                .or(ItemSpecification.itemName(item.getItemName()))
        );
    }
}
