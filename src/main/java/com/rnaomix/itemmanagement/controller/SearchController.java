package com.rnaomix.itemmanagement.controller;

import com.rnaomix.itemmanagement.model.Item;
import com.rnaomix.itemmanagement.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchController {

    private ItemService itemService;

    @Autowired
    public SearchController(ItemService itemService){
        this.itemService = itemService;
    }

    // 初期画面
    @GetMapping("/list")
    public String getItemList(Model model){

        model.addAttribute("items", itemService.getItemList());
        // 検索用のオブジェクトを用意
        model.addAttribute("item", new Item());
        return "/search/list";
    }

    // 検索
    @PostMapping("/list")
    public String searchItemList(@ModelAttribute Item item, Model model){

        model.addAttribute("items", itemService.searchItemList(item));
        return "/search/list";
    }

    // ショッピングカート
    @PostMapping("/cart")
    public String addItemToCart(@ModelAttribute Item item, Model model){

        return "/search/list";
    }
}
