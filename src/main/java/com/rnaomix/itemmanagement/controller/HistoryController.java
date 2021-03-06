package com.rnaomix.itemmanagement.controller;

import com.rnaomix.itemmanagement.service.HistoryService;
import com.rnaomix.itemmanagement.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;

@Controller
@RequestMapping("history")
public class HistoryController {

    private InitController initController;
    private HistoryService historyService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public HistoryController(InitController initController, HistoryService historyService, ShoppingCartService shoppingCartService) {
        this.initController = initController;
        this.historyService = historyService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("list")
    public String getHistories(Model model){

        model.addAttribute("histories", historyService.getNowItemHistoryList());
        model.addAttribute("monthly", historyService.getNowDate());
        initController.initializeSessionInfo(model);
        return "/history/list";
    }

    @PostMapping("list")
    public String getMonthlyHistories(@RequestParam(name = "monthly") String monthly, Model model){

        model.addAttribute("histories", historyService.getMonthlyItemHistoryList(monthly));
        model.addAttribute("monthly", monthly);
        initController.initializeSessionInfo(model);
        return "/history/list";
    }
}
