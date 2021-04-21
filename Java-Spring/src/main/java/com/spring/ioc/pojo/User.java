package com.spring.ioc.pojo;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
    private  static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Integer age;
    private Date birthday;

    public User() {}

    public User(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void init(){
        System.out.println("我是User的初始化方法");
    }
    public void destroy(){
        System.out.println("我是User的销毁化方法");
    }

    public static User getUser(){
        System.out.println("User中的  静态 getUser");
        return  new User();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", birthday=" + birthday +
            '}';
    }
}
