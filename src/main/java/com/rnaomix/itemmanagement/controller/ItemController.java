package com.rnaomix.itemmanagement.controller;

import com.rnaomix.itemmanagement.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("item")
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("list")
    public String getItems(Model model){

        model.addAttribute("items", itemService.getItemList());
        return "item/list";
    }
}
