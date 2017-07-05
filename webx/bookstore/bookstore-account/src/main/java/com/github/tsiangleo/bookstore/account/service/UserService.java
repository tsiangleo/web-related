package com.github.tsiangleo.bookstore.account.service;

import com.github.tsiangleo.bookstore.account.domain.UserInfo;

/**
 * Created by tsiang on 2017/6/13.
 */
public interface UserService {

    void save(UserInfo userInfo) throws ServiceException;

    boolean login(String loginName, String password) throws ServiceException;
}
