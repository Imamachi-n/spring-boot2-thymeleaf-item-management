package com.rnaomix.itemmanagement.controller;

import com.rnaomix.itemmanagement.model.Item;
import com.rnaomix.itemmanagement.model.ItemHistoryDetail;
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
        Integer itemCount = shoppingCartService.getTotal();
        model.addAttribute("cartTotal", itemCount);

        // カートの中身が空の場合
        if(itemCount == 0){
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
        model.addAttribute("cartTotal", shoppingCartService.getTotal());
        return "/cart/confirm";
    }

    @PostMapping("/purchase")
    public String purchaseItems(Model model){

        // TODO: カートの中身を保存
        shoppingCartService.clearCart();
        model.addAttribute("isPurchased", "物品を購入しました。");
        return "/cart/list";
    }
}
