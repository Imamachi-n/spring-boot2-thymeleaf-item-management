package com.rnaomix.itemmanagement.service;

import com.rnaomix.itemmanagement.model.ItemHistory;
import com.rnaomix.itemmanagement.model.ItemHistoryDetail;
import com.rnaomix.itemmanagement.model.User;

import java.util.List;

public interface HistoryService {

    List<ItemHistory> getNowItemHistoryList();

    List<ItemHistory> getMonthlyItemHistoryList(String monthly);

    String getNowDate();

    void saveItemHistory(User user, List<ItemHistoryDetail> itemHistoryDetails);
}
