package com.alibaba.webx.tutorial.app1.dao.impl;

import com.alibaba.webx.tutorial.app1.dao.UserDAO;
import com.alibaba.webx.tutorial.app1.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsiang on 2017/5/27.
 */

@Repository
public class UserDAOImpl implements UserDAO {

    private static List<User> userDB = new ArrayList<User>();

    public void add(User user) {
        userDB.add(user);
    }

    public List<User> listAll() {
        return userDB;
    }
}
