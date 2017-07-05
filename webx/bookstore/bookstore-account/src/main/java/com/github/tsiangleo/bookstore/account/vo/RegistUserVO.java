package com.github.tsiangleo.bookstore.account.vo;

/**
 * Created by tsiang on 2017/6/13.
 */
public class RegistUserVO {
    private String loginName;
    private String password;
    private String password2;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
