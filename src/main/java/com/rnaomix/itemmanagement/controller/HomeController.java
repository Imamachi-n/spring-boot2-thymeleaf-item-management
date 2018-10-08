package com.rnaomix.itemmanagement.controller;

import com.rnaomix.itemmanagement.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public HomeController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    // ショッピングカート内の物品数をSessionから取得
    private void setCartTotal(Model model){
        model.addAttribute("cartTotal", shoppingCartService.getTotal());
    }

    @GetMapping("/home")
    public String getHome(Model model){

        setCartTotal(model);
        return "home";
    }
}
