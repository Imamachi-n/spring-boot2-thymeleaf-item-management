package com.rnaomix.itemmanagement.service;

import com.rnaomix.itemmanagement.form.UserForm;
import com.rnaomix.itemmanagement.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(long userId);

    List<UserForm> getUserList();

    User findUserByUsername(String username);

    void saveUser(User user, boolean isAdmin);
}
