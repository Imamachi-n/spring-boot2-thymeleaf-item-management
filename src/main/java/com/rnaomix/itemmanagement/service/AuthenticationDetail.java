package com.rnaomix.itemmanagement.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

public class AuthenticationDetail extends User {

    public AuthenticationDetail(com.rnaomix.itemmanagement.model.User user){
        super(
                user.getUsername(),
                user.getPassword(),
                true,   // ユーザが有効かどうか
                true,   // アカウントの有効期限が過ぎていないかどうか
                true,   // アカウントの資格情報が有効期限切れでないかどうか
                true,   // アカウントがロックされていないかどうか
                new ArrayList<GrantedAuthority>()
        );
    }
}
