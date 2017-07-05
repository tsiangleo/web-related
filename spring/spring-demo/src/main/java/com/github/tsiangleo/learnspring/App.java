package com.github.tsiangleo.learnspring;

import com.github.tsiangleo.learnspring.bean.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        Car car = applicationContext.getBean(Car.class);
        System.out.println(car.getInfo());
        ((ClassPathXmlApplicationContext)applicationContext).destroy();
    }
}
