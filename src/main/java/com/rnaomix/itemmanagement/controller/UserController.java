package com.rnaomix.itemmanagement.controller;

import com.rnaomix.itemmanagement.form.ItemForm;
import com.rnaomix.itemmanagement.form.UserForm;
import com.rnaomix.itemmanagement.model.Role;
import com.rnaomix.itemmanagement.model.User;
import com.rnaomix.itemmanagement.service.ShoppingCartService;
import com.rnaomix.itemmanagement.service.UserService;
import org.hibernate.exception.ConstraintViolationException;
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

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private InitController initController;

    @Autowired
    public UserController(UserService userService, InitController initController) {
        this.userService = userService;
        this.initController = initController;
    }

    private void init(Model model){
        // 全件検索結果をリクエストスコープで渡す
        model.addAttribute("users", userService.getUserList());
        // フォーム内容を格納するオブジェクトの用意
        model.addAttribute("userForm", new UserForm());
        initController.initializeSessionInfo(model);
    }

    private void initError(Model model){
        // 全件検索結果をリクエストスコープで渡す
        model.addAttribute("users", userService.getUserList());
        initController.initializeSessionInfo(model);
    }

    @GetMapping({"", "/list"})
    public String getUserList(Model model){

        // 初期処理
        init(model);
        return "/user/list";
    }

    @PostMapping("/list")
    public String registerUser(@Validated @ModelAttribute UserForm userForm,
                               BindingResult result,
                               Model model){

        // 入力エラーが存在する場合
        if (result.hasErrors()){
            initError(model);
            model.addAttribute("formWarning", "入力に誤りがあります。");
            return "/user/list";
        }

        // パスワード一致しない
        if (!userForm.getPassword().equals(userForm.getPasswordConfirmation())) {
            initError(model);
            model.addAttribute("inValidPassword", "入力したパスワードが一致しません。");
            return "/user/list";
        }

        model.addAttribute("userForm", userForm);
        initController.initializeSessionInfo(model);
        return "/user/confirm";
    }

    @PostMapping("/confirm")
    public String confirmUserRegistration(@RequestParam(name = "submit") String submit,
                                          @ModelAttribute UserForm userForm, Model model){

        // キャンセルボタンを押した場合
        if (submit.equals("cancel")){
            // 前の画面に戻る
            initError(model);
            return "/user/list";
        }

        // ユーザの登録
        User user = new User(userForm.getUsername(), userForm.getPassword(), userForm.getEmail(),
                userForm.getFirstName(), userForm.getLastName());
        try{
            userService.saveUser(user, userForm.getIsAdmin());
        }catch(DataIntegrityViolationException e){
            // 前の画面に戻る
            initError(model);
            // エラーメッセージ
            model.addAttribute("formError", "対象のユーザ名・メールアドレスはすでに登録されています。");
            return "/user/list";
        }

        // 初期処理
        init(model);
        // ステータス（登録成功）
        model.addAttribute("isRegistered", "ユーザの登録に成功しました。");
        return "/user/list";
    }

    @GetMapping("edit")
    public String editUser(@RequestParam(name = "userId") Integer userId, Model model){

        UserForm userForm = userService.getUserById(userId);
        if (userForm != null){
            model.addAttribute("userForm", userForm);
        }else{
            model.addAttribute("deleteError", "ユーザが存在しないため更新できません。");
            init(model);    // 初期化処理
            return "/user/list";
        }

        initController.initializeSessionInfo(model);
        return "/user/edit";
    }

    @PostMapping("save")
    public String saveUser(@Validated @ModelAttribute UserForm userForm, BindingResult result,
                           @RequestParam String submit, Model model){

        // キャンセル
        // キャンセルボタンを押した場合
        if (submit.equals("cancel")){
            // 前の画面に戻る
            return "redirect:list";
        }

        // 入力エラーが存在する場合
        if (result.hasErrors()) {
            model.addAttribute("userId", userForm.getUserId());
            initController.initializeSessionInfo(model);
            return "/user/edit";
        }

        try {
            userService.updateUser(userForm);
        }catch(OptimisticLockingFailureException e) {
            model.addAttribute("userId", userForm.getUserId());
            model.addAttribute("formError", "別のユーザによって内容が更新されたため、入力内容を反映できませんでした。");
            return "/user/edit";
        }catch(DataIntegrityViolationException e){
            model.addAttribute("userId", userForm.getUserId());
            model.addAttribute("formError", "参照整合性制約に起因するエラーが発生しました。");
            init(model);
            return "/user/list";
        }

        return "redirect:list";
    }

    @PostMapping("/delete")
    public String deleteItems(@RequestParam(name = "userIds", required = false) List<Integer> userIds,
                              Model model){

        // チェックをつけなかった場合
        if (userIds == null || userIds.size() == 0){
            // 初期処理
            init(model);
            // ステータス
            model.addAttribute("isNotChecked", "削除対象が選択されていません。");
            return "/user/list";
        }

        // 対象の物品情報の削除
        try {
            // 削除処理 // TODO: 自身のユーザを削除した場合の処理
            userService.deleteUser(userIds);

            // 成功メッセージ
            model.addAttribute("isDeleted", "ユーザの削除に成功しました。");

        }catch(DataIntegrityViolationException e){

            // エラーメッセージ
            model.addAttribute("deleteError", "対象のユーザは購入履歴に使用されているので削除できません。");
        }

        // 前の画面に戻る
        init(model);
        return "/user/list";
    }
}
