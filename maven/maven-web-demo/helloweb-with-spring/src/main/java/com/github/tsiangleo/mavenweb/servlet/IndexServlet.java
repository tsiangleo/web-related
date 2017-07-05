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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by tsiang on 2017/5/22.
 */
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>\n" +
                "<head>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h2>");
        sb.append("remote addr:"+req.getRemoteAddr()+"<br/>");
        sb.append("method:"+req.getMethod()+"<br/>");
        sb.append("request URI:" + req.getRequestURI());

        // 从application域对象中得到IOC容器的引用
        ServletContext servletContext = getServletContext();
        ApplicationContext ctx = (ApplicationContext) servletContext.getAttribute("ApplicationContext");
        // 从IOC容器中得到需要的bean
        UserService userService = ctx.getBean(UserService.class);

        List<User> userList = userService.listAllUser();
        if(userList != null && !userList.isEmpty()){
            sb.append("<br/><br/>user list:" + Arrays.toString(userList.toArray()));
        }

        sb.append("<br/><br/><a href=\"/registUI.jsp\">regist user</a>");

        sb.append("<br/><br/>generate this page in " + new Date().toString());

        sb.append("</body>\n" +
                "</html>\n");

        resp.getWriter().print(sb.toString());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
