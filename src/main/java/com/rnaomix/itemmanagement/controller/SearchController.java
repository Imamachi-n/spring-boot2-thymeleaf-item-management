package com.rnaomix.itemmanagement.controller;

import com.rnaomix.itemmanagement.model.Item;
import com.rnaomix.itemmanagement.service.ItemService;
import com.rnaomix.itemmanagement.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private ItemService itemService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public SearchController(ItemService itemService, ShoppingCartService shoppingCartService){
        this.itemService = itemService;
        this.shoppingCartService = shoppingCartService;
    }

    // 初期画面
    @GetMapping("/list")
    public String getItemList(Model model){

        model.addAttribute("items", itemService.getItemList());
        // 検索用のオブジェクトを用意
        model.addAttribute("item", new Item());

        model.addAttribute("cartTotal", shoppingCartService.getTotal());
        return "/search/list";
    }

    // 検索
    @PostMapping("/list")
    public String searchItemList(@ModelAttribute Item item, Model model){

        model.addAttribute("items", itemService.searchItemList(item));
        return "/search/list";
    }

//    // ショッピングカート
//    @PostMapping("/add")
//    public String addItemToCart(@ModelAttribute Item item,
//                                @RequestParam(name = "amount") Integer amount,
//                                @RequestParam(name = "itemId") Integer itemId,
//                                Model model){
//
//        shoppingCartService.addShoppingCart(itemId, amount);
//        model.addAttribute("cart", shoppingCartService.getItemsInCart());
//        model.addAttribute("cartTotal", shoppingCartService.getTotal());
//        model.addAttribute("item", item);
//        System.out.println(shoppingCartService.getTotal());
//        return searchItemList(item, model);
//    }
}
