package com.rnaomix.itemmanagement.service;

import com.rnaomix.itemmanagement.model.Item;
import com.rnaomix.itemmanagement.model.ItemHistoryDetail;

import java.util.List;
import java.util.Map;

public interface HistoryDetailService {

    List<ItemHistoryDetail> createItemHistory(Map<Integer, Integer> cartList);

    List<ItemHistoryDetail> getLatest12();
}
