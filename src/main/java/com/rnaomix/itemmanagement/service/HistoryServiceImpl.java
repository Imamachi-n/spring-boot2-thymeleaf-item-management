package com.rnaomix.itemmanagement.service;

import com.rnaomix.itemmanagement.model.ItemHistory;
import com.rnaomix.itemmanagement.repository.ItemHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service
@Transactional(readOnly = true)
public class HistoryServiceImpl implements HistoryService {

    private ItemHistoryRepository itemHistoryRepository;

    @Autowired
    public HistoryServiceImpl(ItemHistoryRepository itemHistoryRepository) {
        this.itemHistoryRepository = itemHistoryRepository;
    }

    @Override
    public List<ItemHistory> getNowItemHistoryList(){
        // 今日の年と月を取得
        LocalDateTime nowDate = LocalDateTime.now();
        int year = nowDate.getYear();
        int month = nowDate.getMonth().getValue();
        return itemHistoryRepository.findByYearAndMonth(year, month);
    }

    @Override
    public List<ItemHistory> getMonthlyItemHistoryList(String monthly){

        int year = parseInt(monthly.split("-")[0]);
        int month = parseInt(monthly.split("-")[1]);
        return itemHistoryRepository.findByYearAndMonth(year, month);
    }

    @Override
    public String getNowDate(){
        LocalDateTime nowDate = LocalDateTime.now();
        int year = nowDate.getYear();
        int month = nowDate.getMonth().getValue();
        return year + "-" + month;
    }
}
