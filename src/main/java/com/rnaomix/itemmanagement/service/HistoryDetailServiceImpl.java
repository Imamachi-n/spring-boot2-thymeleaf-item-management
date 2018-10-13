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
    private ItemHistoryDetailRepository itemHistoryDetailRepository;

    @Autowired
    public HistoryDetailServiceImpl(ItemService itemService, ItemHistoryDetailRepository itemHistoryDetailRepository) {
        this.itemService = itemService;
        this.itemHistoryDetailRepository = itemHistoryDetailRepository;
    }

    @Override
    public List<ItemHistoryDetail> createItemHistory(Map<Integer, Integer> cartList){

        List<ItemHistoryDetail> itemHistoryDetails = new ArrayList<>();
        for(Integer itemId : cartList.keySet()){
            itemHistoryDetails.add(new ItemHistoryDetail(itemService.getItem(itemId), cartList.get(itemId)));
        }

        return itemHistoryDetails;
    }

    @Override
    public List<ItemHistoryDetail> getLatest12(){
        List<Item> test = new ArrayList<>();
        List<ItemHistoryDetail> result = new ArrayList<>();
        itemHistoryDetailRepository.findFirst9ByOrderByOrderDetailIdDesc()
            .forEach(i -> {
                if(!test.contains(i.getItem())){
                    test.add(i.getItem());
                    result.add(i);
                }
        });

        return result;
    }
}
