package com.github.tsiangleo.mavenweb.service;

import com.github.tsiangleo.mavenweb.domain.User;

import java.util.List;

/**
 * Created by tsiang on 2017/5/27.
 */
public interface UserService {
    void addUser(User user);
    List<User> listAllUser();
}
