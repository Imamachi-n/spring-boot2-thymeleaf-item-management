package com.rnaomix.itemmanagement.controller;

import com.rnaomix.itemmanagement.form.UserForm;
import com.rnaomix.itemmanagement.service.ShoppingCartService;
import com.rnaomix.itemmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    public void init(Model model){
        // 全件検索結果をリクエストスコープで渡す
        model.addAttribute("users", userService.getUserList());
        // フォーム内容を格納するオブジェクトの用意
        model.addAttribute("userForm", new UserForm());
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
            return "/user/list";
        }
        return "user/confirm";
    }

    @PostMapping("/delete")
    public String deleteItems(Model model){

        return "user/list";
    }
}
