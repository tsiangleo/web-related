package com.github.tsiangleo.mavenweb.service.impl;

import com.github.tsiangleo.mavenweb.dao.UserDAO;
import com.github.tsiangleo.mavenweb.dao.impl.UserDAOImpl;
import com.github.tsiangleo.mavenweb.domain.User;
import com.github.tsiangleo.mavenweb.service.UserService;

import java.util.List;

/**
 * Created by tsiang on 2017/5/27.
 */
public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();

    public void addUser(User user) {
        userDAO.add(user);
    }

    public List<User> listAllUser() {
        return userDAO.listAll();
    }
}
