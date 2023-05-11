package com.example.security.service;

import com.example.security.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
