package com.rnaomix.itemmanagement.controller;

import com.rnaomix.itemmanagement.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public UserController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    // ショッピングカート内の物品数をSessionから取得
    private void setCartTotal(Model model){
        model.addAttribute("cartTotal", shoppingCartService.getTotal());
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("auth",
                SecurityContextHolder.getContext().getAuthentication()
                        .getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    @GetMapping("list")
    public String getUserList(Model model){

        setCartTotal(model);
        return "/user/list";
    }
}
