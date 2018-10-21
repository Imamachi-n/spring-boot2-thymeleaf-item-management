package com.rnaomix.itemmanagement.controller;

import com.rnaomix.itemmanagement.form.ItemForm;
import com.rnaomix.itemmanagement.model.Item;
import com.rnaomix.itemmanagement.service.ItemService;
import com.rnaomix.itemmanagement.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("item")
public class ItemController {

    private ItemService itemService;
    private InitController initController;

    @Autowired
    public ItemController(ItemService itemService, InitController initController) {
        this.itemService = itemService;
        this.initController = initController;
    }

    private void init(Model model){
        // 全件検索結果をリクエストスコープで渡す
        model.addAttribute("items", itemService.getItemList());
        // フォーム内容のオブジェクトを用意
        model.addAttribute("itemForm", new ItemForm());
        initController.initializeSessionInfo(model);
    }

    private void initGetItems(Model model){
        // 全件検索結果をリクエストスコープで渡す
        model.addAttribute("items", itemService.getItemList());
        initController.initializeSessionInfo(model);
    }

    @GetMapping({"", "/list"})
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
        initController.initializeSessionInfo(model);
        return "item/confirm";
    }

    @PostMapping("/confirm")
    public String confirmItemRegistration(@RequestParam(name = "submit") String submit, @ModelAttribute ItemForm itemForm, Model model){

        // キャンセルボタンを押した場合
        if (submit.equals("cancel")){
            // 前の画面に戻る
            initGetItems(model);
            return "item/list";
        }

        // 物品の登録
        Item item = new Item(itemForm.getCatId(), itemForm.getItemName(), itemForm.getPrice());
        try {
            itemService.saveItem(item);
        }catch(DataIntegrityViolationException e){
            // 前の画面に戻る
            init(model);
            // エラーメッセージ
            model.addAttribute("formError", "対象のカタログIDはすでに登録されています。");
            return "item/list";
        }

        // 初期処理
        init(model);
        // ステータス（登録成功）
        model.addAttribute("isRegistered", "物品の登録に成功しました。");
        return "item/list";
    }

    @GetMapping("edit")
    public String editItem(@RequestParam(name = "itemId") Integer itemId, Model model){

        Item item = itemService.getItem(itemId);
        ItemForm itemForm = new ItemForm(itemId, item.getCatId(), item.getItemName(), item.getPrice(), item.getVersion());
        model.addAttribute("itemForm", itemForm);
        initController.initializeSessionInfo(model);
        return "item/edit";
    }

    @PostMapping("/save")
    public String saveItem(@Validated @ModelAttribute ItemForm itemForm, BindingResult result, @RequestParam String submit, Model model){

        // キャンセル
        // キャンセルボタンを押した場合
        if (submit.equals("cancel")){
            // 前の画面に戻る
            return "redirect:list";
        }

        // 入力エラーが存在する場合
        if (result.hasErrors()) {
            model.addAttribute("itemId", itemForm.getItemId());
            initController.initializeSessionInfo(model);
            return "/item/edit";
        }

        // 物品の登録
        Item item = new Item(itemForm.getItemId(), itemForm.getCatId(), itemForm.getItemName(), itemForm.getPrice(), java.sql.Date.valueOf(LocalDate.now()), itemForm.getVersion());

        try {
            itemService.saveItem(item);
        }catch(OptimisticLockingFailureException e) {
            model.addAttribute("itemId", itemForm.getItemId());
            model.addAttribute("formError", "別のユーザによって内容が更新されたため、入力内容を反映できませんでした。");
            return "/item/edit";
        }
        return "redirect:list";
    }

    @PostMapping("/delete")
    public String deleteItems(@RequestParam(name = "itemIds", required = false) List<Integer> itemIds, Model model){

        // チェックをつけなかった場合
        if (itemIds == null || itemIds.size() == 0){
            // 初期処理
            init(model);
            // ステータス
            model.addAttribute("isNotChecked", "削除対象が選択されていません。");
            return "item/list";
        }

        // 対象の物品情報の削除
        try {
            // 削除処理
            itemService.deleteItem(itemIds);

            // 成功メッセージ
            model.addAttribute("isDeleted", "物品の削除に成功しました。");

        }catch(DataIntegrityViolationException e){

            // エラーメッセージ
            model.addAttribute("deleteError", "対象の物品は購入履歴に使用されているので削除できません。");
        }

        // 前の画面に戻る
        init(model);
        return "item/list";
    }
}
