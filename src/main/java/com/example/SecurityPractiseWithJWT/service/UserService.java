package com.example.SecurityPractiseWithJWT.service;

import com.example.SecurityPractiseWithJWT.entity.User;

import java.util.Set;

public interface UserService {
    String registerUser(User user);
    Set<String> getRoles(String username);
}
