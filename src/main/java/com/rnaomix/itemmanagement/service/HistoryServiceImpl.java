package com.rnaomix.itemmanagement.service;

import com.rnaomix.itemmanagement.model.ItemHistory;
import com.rnaomix.itemmanagement.repository.ItemHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class HistoryServiceImpl implements HistoryService {

    private ItemHistoryRepository itemHistoryRepository;

    @Autowired
    public HistoryServiceImpl(ItemHistoryRepository itemHistoryRepository) {
        this.itemHistoryRepository = itemHistoryRepository;
    }

    @Override
    public List<ItemHistory> getItemHistoryList(){
        return itemHistoryRepository.findAll();
    }
}
