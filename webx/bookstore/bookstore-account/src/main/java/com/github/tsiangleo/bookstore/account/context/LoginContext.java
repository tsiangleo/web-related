package com.github.tsiangleo.bookstore.account.context;

import java.io.Serializable;

/**
 *
 * 保存在session中，代表每个登陆用户。
 *
 * Created by tsiang on 2017/6/13.
 */
public class LoginContext implements Serializable {
    private static final long                      serialVersionUID = -7507510429755782596L;

    /**
     * 登录名，具有唯一性。
     */
    private String loginName;

    /**
     * 其他字段
     */
    private String[] roles; //角色


    public LoginContext(){}

    public LoginContext(String loginName){
        this.loginName = loginName;
    }

    public LoginContext(String loginName,String[] roles){
        this.loginName = loginName;
        this.roles = roles;
    }


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


}
