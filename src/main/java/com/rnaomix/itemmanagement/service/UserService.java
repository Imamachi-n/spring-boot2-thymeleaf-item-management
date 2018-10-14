package com.rnaomix.itemmanagement.service;

import com.rnaomix.itemmanagement.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(long userId);

    User findUserByUsername(String username);

    void saveUser(User user);
}
