package com.powell.security.service;

import com.powell.security.domain.User;

import java.util.Optional;


public interface UserService {
    public Optional<User> getByUsername(String username);
}
