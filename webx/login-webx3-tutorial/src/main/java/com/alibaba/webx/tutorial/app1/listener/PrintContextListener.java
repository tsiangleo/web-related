package com.alibaba.webx.tutorial.app1.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Arrays;

/**
 * Created by tsiang on 2017/5/30.
 */
public class PrintContextListener implements ServletContextListener {
    private ApplicationContext applicationContext;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log("################### enter PrintContextListener.contextInitialized() ####################");

        ServletContext servletContext = servletContextEvent.getServletContext();
        applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);

        do {
            log("\ncontext info:");
            log("context id:" + applicationContext.getId());
            log("context DisplayName:" + applicationContext.getDisplayName());
            String[] names = applicationContext.getBeanDefinitionNames();
            log("context BeanDefinitionNames:"+Arrays.toString(names));

        }while ((applicationContext = applicationContext.getParent()) != null);

        log("###################  exit PrintContextListener.contextInitialized() ####################");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    public void log(String s){
        System.out.println(s);
    }

}
