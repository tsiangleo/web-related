package com.github.tsiangleo.mavenweb.servlet;

import com.github.tsiangleo.mavenweb.domain.User;
import com.github.tsiangleo.mavenweb.service.UserService;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tsiang on 2017/5/27.
 */
public class RegistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doPost(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String loginName = req.getParameter("loginName");
        String password = req.getParameter("password");

        User user = new User();
        user.setLoginName(loginName);
        user.setPassword(password);

        // 从application域对象中得到IOC容器的引用
        ServletContext servletContext = getServletContext();
        ApplicationContext ctx = (ApplicationContext) servletContext.getAttribute("ApplicationContext");
        // 从IOC容器中得到需要的bean
        UserService userService = ctx.getBean(UserService.class);

        userService.addUser(user);

        req.setAttribute("opTips","user ["+loginName+"] successfully registed.");
        req.getRequestDispatcher("/ok.jsp").forward(req,resp);
    }
}