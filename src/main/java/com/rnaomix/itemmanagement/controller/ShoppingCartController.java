package com.rnaomix.itemmanagement.controller;

import com.rnaomix.itemmanagement.model.Item;
import com.rnaomix.itemmanagement.service.ItemService;
import com.rnaomix.itemmanagement.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    private ItemService itemService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ItemService itemService, ShoppingCartService shoppingCartService){
        this.itemService = itemService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("list")
    public String getCart(Model model){

        model.addAttribute("item", new Item());
        model.addAttribute("cart", shoppingCartService.getItemsInCart());
        model.addAttribute("cartTotal", shoppingCartService.getTotal());
        return "/cart/list";
    }

    // ショッピングカート
    @PostMapping("/add")
    public String addItemToCart(@ModelAttribute Item item,
                                @RequestParam(name = "amount") Integer amount,
                                @RequestParam(name = "itemId") Integer itemId,
                                Model model){

        shoppingCartService.addShoppingCart(itemId, amount);
        model.addAttribute("cart", shoppingCartService.getItemsInCart());
        model.addAttribute("cartTotal", shoppingCartService.getTotal());
        System.out.println(shoppingCartService.getTotal());
        return "redirect:/search/list";
    }
}
