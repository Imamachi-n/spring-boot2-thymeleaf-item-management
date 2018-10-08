package com.rnaomix.itemmanagement.controller;

import com.rnaomix.itemmanagement.model.Item;
import com.rnaomix.itemmanagement.service.HistoryDetailService;
import com.rnaomix.itemmanagement.service.ItemService;
import com.rnaomix.itemmanagement.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    private HistoryDetailService historyDetailService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(HistoryDetailService historyDetailService, ShoppingCartService shoppingCartService){
        this.historyDetailService = historyDetailService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("list")
    public String getCart(Model model){

        model.addAttribute("cart", historyDetailService.createItemHistory(shoppingCartService.getItemsInCart()));
        model.addAttribute("cartTotal", shoppingCartService.getTotal());
        return "/cart/list";
    }

    // ショッピングカート
    @PostMapping("/add")
    public String addItemToCart(@RequestParam(name = "amount") Integer amount,
                                @RequestParam(name = "itemId") Integer itemId,
                                @RequestParam(name = "searchItems") String searchItems,
                                Model model){

        shoppingCartService.addShoppingCart(itemId, amount);
        model.addAttribute("cart", shoppingCartService.getItemsInCart());
        model.addAttribute("cartTotal", shoppingCartService.getTotal());
        model.addAttribute("searchItems", searchItems);
        System.out.println(itemId + ": " + shoppingCartService.getTotal());
        return "/search/list";
    }
}
