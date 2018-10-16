package com.rnaomix.itemmanagement.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AuthenticationDetail extends User {

    public AuthenticationDetail(com.rnaomix.itemmanagement.model.User user){
        super(
                user.getUsername(), // ユーザ名
                user.getPassword(), // パスワード
                true,   // ユーザが有効かどうか
                true,   // アカウントの有効期限が過ぎていないかどうか
                true,   // アカウントの資格情報が有効期限切れでないかどうか
                true,   // アカウントがロックされていないかどうか
                user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                    .collect(Collectors.toList())   // 認可情報のリスト
        );
    }
}
