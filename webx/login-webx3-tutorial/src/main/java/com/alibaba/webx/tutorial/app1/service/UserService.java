package com.alibaba.webx.tutorial.app1.service;


import com.alibaba.webx.tutorial.app1.domain.User;

import java.util.List;

/**
 * Created by tsiang on 2017/5/27.
 */
public interface UserService {
    void addUser(User user);
    List<User> listAllUser();
    String getCurrentTime();
}
