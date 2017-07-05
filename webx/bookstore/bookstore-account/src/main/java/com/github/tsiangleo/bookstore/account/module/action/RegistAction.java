package com.github.tsiangleo.bookstore.account.module.action;

import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;

import com.github.tsiangleo.bookstore.account.domain.UserInfo;
import com.github.tsiangleo.bookstore.account.service.ServiceException;
import com.github.tsiangleo.bookstore.account.service.UserService;
import com.github.tsiangleo.bookstore.account.vo.RegistUserVO;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistAction {

    @Autowired
    private UserService userService;

    public void doRegist(@FormGroup("regist") RegistUserVO visitor, Navigator nav) {

        UserInfo userInfo = new UserInfo();
        userInfo.setLoginName(visitor.getLoginName());
        userInfo.setPassword(visitor.getPassword());
        try {
            userService.save(userInfo);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        nav.redirectTo("app1Link").withTarget("welcome");
    }
}
