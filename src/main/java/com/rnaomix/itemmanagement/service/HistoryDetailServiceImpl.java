package com.rnaomix.itemmanagement.service;

import com.rnaomix.itemmanagement.model.Item;
import com.rnaomix.itemmanagement.model.ItemHistoryDetail;
import com.rnaomix.itemmanagement.repository.ItemHistoryDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

@Service
@Transactional(readOnly = true)
public class HistoryDetailServiceImpl implements HistoryDetailService{

    private ItemService itemService;

    @Autowired
    public HistoryDetailServiceImpl(ItemService itemService){
        this.itemService = itemService;
    }

    @Override
    public List<ItemHistoryDetail> createItemHistory(Map<Integer, Integer> cartList){

        List<ItemHistoryDetail> itemHistoryDetails = new ArrayList<>();
        for(Integer itemId : cartList.keySet()){
            itemHistoryDetails.add(new ItemHistoryDetail(itemService.getItem(itemId), cartList.get(itemId)));
        }

        return itemHistoryDetails;
    }
}
