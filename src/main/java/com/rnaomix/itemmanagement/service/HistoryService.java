package com.rnaomix.itemmanagement.service;

import com.rnaomix.itemmanagement.model.ItemHistory;

import java.util.List;

public interface HistoryService {

    List<ItemHistory> getItemHistoryList();
}