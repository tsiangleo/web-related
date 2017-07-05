package com.github.tsiangleo.mavenweb.dao;

import com.github.tsiangleo.mavenweb.domain.User;

import java.util.List;


/**
 * Created by tsiang on 2017/5/27.
 */
public interface UserDAO {
    void add(User user);
    List<User> listAll();
}
