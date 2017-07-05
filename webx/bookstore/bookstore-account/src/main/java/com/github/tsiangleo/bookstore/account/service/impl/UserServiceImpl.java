package com.github.tsiangleo.bookstore.account.service.impl;

import com.github.tsiangleo.bookstore.account.domain.UserInfo;
import com.github.tsiangleo.bookstore.account.service.ServiceException;
import com.github.tsiangleo.bookstore.account.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsiang on 2017/6/13.
 */
public class UserServiceImpl implements UserService {

    private static List<UserInfo> userInfoList = new ArrayList<UserInfo>();

    @Override
    public void save(UserInfo userInfo) throws ServiceException {
        userInfoList.add(userInfo);
    }

    @Override
    public boolean login(String loginName, String password) throws ServiceException {

        for(UserInfo u : userInfoList){
            if(u.getLoginName().equals(loginName) && u.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
