package com.github.tsiangleo.mavenweb.service.impl;

import com.github.tsiangleo.mavenweb.dao.UserDAO;
import com.github.tsiangleo.mavenweb.dao.impl.UserDAOImpl;
import com.github.tsiangleo.mavenweb.domain.User;
import com.github.tsiangleo.mavenweb.service.UserService;
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

//    public UserDAO getUserDAO() {
//        return userDAO;
//    }
//
//    public void setUserDAO(UserDAO userDAO) {
//        this.userDAO = userDAO;
//    }

    public void addUser(User user) {
        userDAO.add(user);
    }

    public List<User> listAllUser() {
        return userDAO.listAll();
    }
}
