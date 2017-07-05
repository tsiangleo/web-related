package com.github.tsiangleo.bookstore.book.module.action;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.alibaba.citrus.util.StringUtil;
import com.github.tsiangleo.bookstore.book.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginAction {

//    @Autowired
//    private HttpServletRequest request;
//
//    @Autowired
//    private UserService userService;
//
//    public void doLogin(@FormGroup("login") LoginUserVO visitor, Navigator nav,Context context) {
//        boolean isSuccess = false;
//        try {
//            isSuccess = userService.login(visitor.getLoginName(),visitor.getPassword());
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
//
//        if(isSuccess) {
//            HttpSession session = request.getSession();
//
//
//            //从session中获取登陆用户信息
//            LoginContext loginContext = (LoginContext)session.getAttribute(AccountConstants.USER_SESSION_KEY);
//            if(loginContext == null){
//                System.out.println("############# LoginAction.create new loginContext -> Current Thread Id:" + Thread.currentThread().getId()+",URL:"+request.getRequestURL());
//                loginContext = new LoginContext(visitor.getLoginName());
//                session.setAttribute(AccountConstants.USER_SESSION_KEY,loginContext);
//            }
////            UserInfoUtil.setLoginContext(loginContext);
////            System.out.println("############# LoginAction.doLogin() -> Current Thread Id:" + Thread.currentThread().getId() + ",setting LoginContext in ThreadLocal,URL:" + request.getRequestURL());
//
//            //重定向
//            String redirectURL = getRedirectURL();
//            System.out.println("LoginAction.doLogin()###### redirectURL:" + redirectURL);
//
//            if(redirectURL != null){
//                nav.redirectToLocation(redirectURL);
//            }else {
//                nav.redirectTo("app1Link").withTarget("welcome");
//            }
//        }
//        else{
//            context.put("loginError", "登陆失败");
//            //todo 带上之前的redirectURL
//        }
//    }
//
//
//    public void doLogout(HttpSession session, Navigator nav) throws Exception {
//        // 清除session中的user
//        session.removeAttribute(AccountConstants.USER_SESSION_KEY);
//
//        nav.redirectTo("app1Link").withTarget("index");
//    }
//
//
//    private String getRedirectURL(){
//        String queryString = request.getQueryString();
//        String key = AccountConstants.REDIRECT_URL + "=";
//
//        System.out.println("LoginAction.getRedirectURL()###### queryString:"+queryString+",key:"+key);
//
//        if(queryString == null || !queryString.contains(key)){
//            return null;
//        }
//        System.out.println("LoginAction.getRedirectURL()###### queryString.indexOf(key):" + queryString.indexOf(key));
//
//        String url =  queryString.substring(queryString.indexOf(key)+key.length());
//        if(url == null || StringUtil.isBlank(url)){
//            return null;
//        }
//        return url;
//    }
}
