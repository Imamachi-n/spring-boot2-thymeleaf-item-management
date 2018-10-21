package com.rnaomix.itemmanagement.controller;

import com.rnaomix.itemmanagement.form.ItemForm;
import com.rnaomix.itemmanagement.form.UserForm;
import com.rnaomix.itemmanagement.model.User;
import com.rnaomix.itemmanagement.service.ShoppingCartService;
import com.rnaomix.itemmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
            model.addAttribute("formError", "入力に誤りがあります。");
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
        return "user/confirm";
    }

    @PostMapping("/confirm")
    public String confirmUserRegistration(@RequestParam(name = "submit") String submit,
                                          @ModelAttribute UserForm userForm, Model model){

        // キャンセルボタンを押した場合
        if (submit.equals("cancel")){
            // 前の画面に戻る
            initError(model);
            return "user/list";
        }

        // ユーザの登録
        User user = new User(userForm.getUsername(), userForm.getPassword(), userForm.getEmail(),
                userForm.getFirstName(), userForm.getLastName());

        // 初期処理
        init(model);
        // ステータス（登録成功）
        model.addAttribute("isRegistered", "ユーザの登録に成功しました。");
        return "/user/list";
    }

    @PostMapping("/delete")
    public String deleteItems(Model model){

        return "user/list";
    }
}
