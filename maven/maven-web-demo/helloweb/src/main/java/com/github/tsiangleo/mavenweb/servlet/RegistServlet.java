package com.github.tsiangleo.mavenweb.servlet;

import com.github.tsiangleo.mavenweb.domain.User;
import com.github.tsiangleo.mavenweb.service.UserService;
import com.github.tsiangleo.mavenweb.service.impl.UserServiceImpl;

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

        UserService userService = new UserServiceImpl();
        userService.addUser(user);

        req.setAttribute("opTips","user ["+loginName+"] successfully registed.");
        req.getRequestDispatcher("/ok.jsp").forward(req,resp);
    }
}