package com.spring.ioc.pojo.annotation;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 声明该类为配置类
public class Factory {
    @Bean(initMethod = "init",destroyMethod = "destroy")
    public User createUser(){
        System.out.println("Factory 中的  非静态 createUser");
        return  new User();
    }
    public void init(){
        System.out.println("我是User的初始化方法");
    }
    public void destroy(){
        System.out.println("我是User的销毁化方法");
    }
}
