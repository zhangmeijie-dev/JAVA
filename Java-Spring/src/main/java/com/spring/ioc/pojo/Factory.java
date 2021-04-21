package com.spring.ioc.pojo;


public class Factory {
    public User createUser(){
        System.out.println("Factory 中的  非静态 createUser");
        return  new User();
    }
}
