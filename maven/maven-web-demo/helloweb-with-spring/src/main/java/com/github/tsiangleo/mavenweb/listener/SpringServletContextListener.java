package com.github.tsiangleo.mavenweb.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by tsiang on 2017/5/27.
 */
public class SpringServletContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        // 1����ȡspring�����ļ������ƺ�λ��
        ServletContext servletContext = sce.getServletContext();
        /**1��getInitParameter()��������GenericServlet�ӿ����¶����һ���������������ó�ʼ����web.xml�д�ŵĲ�����
         ���ͨ����web.xml�е�ServletContext�������ж����������ô����webӦ�ó����е�servlet���ɵ��ã�web.xml�еĸ�ʽΪ��
         <context-param>
         <param-name>test</param-name>
         <param-value>Is it me</param-value>
         < context -param>
         2������<context-param>�еĲ���,���ø�ʽΪ��
         String name =getServletContext(). getInitParameter(��name��); ��
         String name = getServletConfig().getServletContext().getInitParameter(��name��);
         */
        String config = servletContext.getInitParameter("configLocation");
        // 2������IOC����
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        // 3����IOC��������ServletContext��һ��������
        servletContext.setAttribute("ApplicationContext",ctx);

        System.out.println("=========================\n\n" +
                "SpringServletContextListener.contextInitialized()\n"
                        + "configLocation:"+config +
                "\n\n======================");

    }

    public void contextDestroyed(ServletContextEvent sce) {


    }
}
