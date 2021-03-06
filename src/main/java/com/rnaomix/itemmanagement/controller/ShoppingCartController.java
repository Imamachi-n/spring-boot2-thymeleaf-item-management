package com.rnaomix.itemmanagement.controller;

import com.rnaomix.itemmanagement.model.Item;
import com.rnaomix.itemmanagement.model.ItemHistoryDetail;
import com.rnaomix.itemmanagement.model.User;
import com.rnaomix.itemmanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    private InitController initController;
    private HistoryService historyService;
    private HistoryDetailService historyDetailService;
    private ShoppingCartService shoppingCartService;
    private UserService userService;

    @Autowired
    public ShoppingCartController(InitController initController, HistoryService historyService, HistoryDetailService historyDetailService, ShoppingCartService shoppingCartService, UserService userService) {
        this.initController = initController;
        this.historyService = historyService;
        this.historyDetailService = historyDetailService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @GetMapping({"", "/list"})
    public String getCart(Model model){

        model.addAttribute("cart", historyDetailService.createItemHistory(shoppingCartService.getItemsInCart()));
        initController.initializeSessionInfo(model);

        // カートの中身が空の場合
        if(shoppingCartService.getTotal() == 0){
            model.addAttribute("isEmpty", "カートの中身が入っていません。");
        }

        return "/cart/list";
    }

    @PostMapping("/add")
    public String addItemToCart(@RequestParam(name = "amount") Integer amount,
                                @RequestParam(name = "itemId") Integer itemId,
                                Model model){

        shoppingCartService.addShoppingCart(itemId, amount);
        return "redirect:/cart/list";
    }

    @PostMapping("/delete")
    public String deleteItemFromCart(@RequestParam(name = "itemId") Integer itemId, Model model){

        shoppingCartService.removeShoppingCart(itemId);

        // カートの中身が空の場合
        if(shoppingCartService.getTotal() == 0){
            model.addAttribute("isEmpty", "カートの中身が入っていません。");
        }
        return "redirect:/cart/list";
    }

    @PostMapping("/confirm")
    public String confirmCart(Model model){

        model.addAttribute("cart", historyDetailService.createItemHistory(shoppingCartService.getItemsInCart()));
        initController.initializeSessionInfo(model);
        return "/cart/confirm";
    }

    @PostMapping("/purchase")
    public String purchaseItems(Model model){

        // FIXME: 戻り値Nullの処理を考慮するべき -> Optionalを使う
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());

            // カートの中身を保存
            Map<Integer, Integer> cartItems = shoppingCartService.getItemsInCart();
            historyService.saveItemHistory(user, historyDetailService.createItemHistory(cartItems));
            shoppingCartService.clearCart();

        model.addAttribute("isPurchased", "物品を購入しました。");
        initController.initializeSessionInfo(model);
        return "/cart/list";
    }
}
