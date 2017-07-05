package com.alibaba.webx.tutorial.app1.service.impl;

import com.alibaba.webx.tutorial.app1.dao.UserDAO;
import com.alibaba.webx.tutorial.app1.domain.User;
import com.alibaba.webx.tutorial.app1.service.UserService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tsiang on 2017/5/27.
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    public void addUser(User user) {
        userDAO.add(user);
    }

    public List<User> listAllUser() {
        return userDAO.listAll();
    }

    @Override
    public String getCurrentTime() {
       return  new java.util.Date().toString();
    }
}
