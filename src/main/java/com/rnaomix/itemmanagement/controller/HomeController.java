package com.rnaomix.itemmanagement.controller;

import com.rnaomix.itemmanagement.service.HistoryDetailService;
import com.rnaomix.itemmanagement.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class HomeController {

    private InitController initController;
    private ShoppingCartService shoppingCartService;
    private HistoryDetailService historyDetailService;

    @Autowired
    public HomeController(InitController initController, ShoppingCartService shoppingCartService, HistoryDetailService historyDetailService) {
        this.initController = initController;
        this.shoppingCartService = shoppingCartService;
        this.historyDetailService = historyDetailService;
    }

    @GetMapping("")
    public String getHome(Model model){

        model.addAttribute("histories", historyDetailService.getLatest12());
        // FIXME: テスト用
        historyDetailService.getLatest12().forEach(a -> {
            System.out.println(a.getItem().getItemId() + " : "
                    + a.getItem().getItemName() + " : "
                    + a.getItem().getPrice());
        });

        initController.initializeSessionInfo(model);
        return "/home";
    }

    @PostMapping("/add")
    public String addItemToCart(@RequestParam(name = "itemId") Integer itemId,
                                @RequestParam(name = "itemName") String itemName,
                                Model model){

        shoppingCartService.addShoppingCart(itemId, 1);
        model.addAttribute("isAdded", "「" + itemName + "」×1をカートに追加しました。");

        return getHome(model);
    }
}
