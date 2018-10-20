package com.rnaomix.itemmanagement.controller;

import com.rnaomix.itemmanagement.service.HistoryService;
import com.rnaomix.itemmanagement.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private HistoryService historyService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public HistoryController(HistoryService historyService, ShoppingCartService shoppingCartService) {
        this.historyService = historyService;
        this.shoppingCartService = shoppingCartService;
    }

    // ショッピングカート内の物品数をSessionから取得
    private void setCartTotal(Model model){
        model.addAttribute("cartTotal", shoppingCartService.getTotal());
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @GetMapping("list")
    public String getHistories(Model model){

        model.addAttribute("histories", historyService.getNowItemHistoryList());
        model.addAttribute("monthly", historyService.getNowDate());
        setCartTotal(model);
        return "/history/list";
    }

    @PostMapping("list")
    public String getMonthlyHistories(@RequestParam(name = "monthly") String monthly, Model model){

        model.addAttribute("histories", historyService.getMonthlyItemHistoryList(monthly));
        model.addAttribute("monthly", monthly);
        return "/history/list";
    }
}
