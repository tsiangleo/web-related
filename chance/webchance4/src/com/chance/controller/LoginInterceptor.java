package com.chance.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.chance.domain.Admin;

/**
 * 登陆拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
	private static final String[] IGNORE_URI = {"/login", "/LoginUI","/static"};
	 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        String url = request.getRequestURL().toString();
        for (String s : IGNORE_URI) {
            if (url.contains(s)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
        	Admin admin = (Admin) request.getSession().getAttribute("admin");
            if (admin != null) 
            	flag = true;
        }
        
        //经过上面的逻辑仍然为false，则跳转到登陆页面。
        if(!flag){
        	response.sendRedirect(request.getContextPath()+"/admin/login?securityMsg=please login first");
        }
        return flag;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
