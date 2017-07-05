package com.alibaba.webx.tutorial.app1.dao;


import com.alibaba.webx.tutorial.app1.domain.User;

import java.util.List;


/**
 * Created by tsiang on 2017/5/27.
 */
public interface UserDAO {
    void add(User user);
    List<User> listAll();
}
