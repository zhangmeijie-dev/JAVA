package com.spring.ioc;

import com.spring.ioc.pojo.*;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class IOC {
    /**
     *  spring   ---  xml 文件注入
     */
    @Test
    public void method01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/ioc/SpringIOCXML.xml");
        Factory factory = (Factory)context.getBean("beanFactory"); // 空参构造器
        User user0 = (User)context.getBean("user0"); // 指定构造方法
        User user1 = (User)context.getBean("user1"); // 指定初始化方法和销毁方法
        User user2 = (User)context.getBean("user2"); // 被创建对象中的静态方法
        User user3 = (User)context.getBean("user3"); // 容器中任意对象的非静态方法

        context.close();
    }

    /**
     *  spring   ---  注解
     */
    @Test
    public void method02() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/spring/ioc/SpringIOCAnnotation.xml");
        com.spring.ioc.pojo.annotation.User  user0 = (com.spring.ioc.pojo.annotation.User )context.getBean("createUser");
        com.spring.ioc.pojo.annotation.User  user1 = (com.spring.ioc.pojo.annotation.User )context.getBean("user");
        context.close();
    }



}