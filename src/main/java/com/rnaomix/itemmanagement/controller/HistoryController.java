package com.rnaomix.itemmanagement.controller;

import com.rnaomix.itemmanagement.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("history")
public class HistoryController {

    private HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("list")
    public String getHistories(Model model){

        model.addAttribute("histories", historyService.getItemHistoryList());

        return "/history/list";
    }
}
