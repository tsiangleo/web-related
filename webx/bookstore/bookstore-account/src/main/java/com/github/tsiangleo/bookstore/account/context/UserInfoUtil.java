package com.github.tsiangleo.bookstore.account.context;

/**
 * Created by tsiang on 2017/6/13.
 */
public class UserInfoUtil {

    private static ThreadLocal<LoginContext> userHolder = new ThreadLocal<LoginContext>(){
        @Override
        protected LoginContext initialValue() {
            return new LoginContext();
        }

    };

    public static final LoginContext getLoginContext() {
        return userHolder.get();
    }

    public static final void setLoginContext(LoginContext user) {
        userHolder.set(user);
    }
}
