package com.rnaomix.itemmanagement.controller;

import com.rnaomix.itemmanagement.form.ItemForm;
import com.rnaomix.itemmanagement.model.Item;
import com.rnaomix.itemmanagement.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("item")
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    private void init(Model model){
        // 全件検索結果をリクエストスコープで渡す
        model.addAttribute("items", itemService.getItemList());
        // フォーム内容のオブジェクトを用意
        model.addAttribute("itemForm", new ItemForm());
    }

    private void initGetItems(Model model){
        // 全件検索結果をリクエストスコープで渡す
        model.addAttribute("items", itemService.getItemList());
    }

    @GetMapping("/list")
    public String getItems(Model model){

        // 初期処理
        init(model);

        return "item/list";
    }

    @PostMapping("/list")
    public String registerItem(@Validated @ModelAttribute ItemForm itemForm, BindingResult result, Model model){

        // 入力エラーが存在する場合
        if (result.hasErrors()) {
            // 全件検索結果をリクエストスコープで渡す
            initGetItems(model);
            return "item/list";
        }

        model.addAttribute("itemForm", itemForm);
        return "item/confirm";
    }

    @PostMapping("/confirm")
    public String confirmItemRegistration(@RequestParam(name = "submit") String submit, @ModelAttribute ItemForm itemForm, Model model){

        if (submit.equals("cancel")){
            // 全件検索結果をリクエストスコープで渡す
            initGetItems(model);
            return "item/list";
        }

        // 物品の登録
        Item item = new Item(itemForm.getCatId(), itemForm.getItemName(), itemForm.getPrice());
        itemService.saveItem(item);

        // 初期処理
        init(model);
        // ステータス（登録成功）
        model.addAttribute("isRegistered", true);
        return "item/list";
    }

    @PostMapping("/delete")
    public String deleteItems(@RequestParam(name = "itemIds", required = false)Set<Integer> itemIds, Model model){

        if (itemIds == null || itemIds.size() == 0){
            // 初期処理
            init(model);
            // ステータス
            model.addAttribute("isNotChecked", true);
            return "item/list";
        }

        // 対象の物品情報の削除
        itemService.deleteItem(itemIds);
        return "redirect:list";
    }
}
