package com.rnaomix.itemmanagement.controller;

import com.rnaomix.itemmanagement.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchController {

    private ItemService itemService;

    @Autowired
    public SearchController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping("/list")
    public String getSearchList(Model model){

        model.addAttribute("items", itemService.getItemList());
        return "/search/list";
    }
}
