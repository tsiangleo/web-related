package com.github.tsiangleo.learnspring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by tsiang on 2017/5/29.
 */

@Component
public class Car implements InitializingBean,DisposableBean,BeanNameAware,BeanFactoryAware{
    @Autowired
    private String band;
    @Autowired
    private Integer maxSpeed;

    public Car(){
        System.out.println("Car()");
    }

    public String getInfo() {
        return "Car Info{" +
                "band='" + band + '\'' +
                ", maxSpeed=" + maxSpeed +
                '}';
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean.afterPropertiesSet()");
    }

    public void destroy() throws Exception {
        System.out.println("DisposableBean.destroy()");
    }

    public void setBeanName(String name) {
        System.out.println("BeanNameAware.setBeanName()");
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware.setBeanFactory()");
    }

    @PostConstruct
    public void myInit(){
        System.out.println("myInit()");
    }

    @PreDestroy
    public void myDestroy(){
        System.out.println("myDestroy()");
    }


    @Override
    public String toString() {
        return "Car{" +
                "band='" + band + '\'' +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}
