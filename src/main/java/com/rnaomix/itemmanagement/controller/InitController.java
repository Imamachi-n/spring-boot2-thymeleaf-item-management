package com.rnaomix.itemmanagement.controller;

import com.rnaomix.itemmanagement.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class InitController {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public InitController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    // Sessionからの引き継ぎ情報
    public void initializeSessionInfo(Model model){
        // カート内の物品数
        model.addAttribute("cartTotal", shoppingCartService.getTotal());
        // ログインユーザ名
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        // ログインユーザがADMIN権限を持つかどうか
        model.addAttribute("auth",
                SecurityContextHolder.getContext().getAuthentication()
                        .getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }
}
