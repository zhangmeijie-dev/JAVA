package com.spring.ioc.pojo;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private Integer age;
    private Date birthday;
    private String value;

    public void init(){
        System.out.println("我是Person的初始化方法");
    }
    public void destroy(){
        System.out.println("我是Person的销毁化方法");
    }

    public Person() {
    }

    public Person(String name, Integer age, Date birthday, String value) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", birthday=" + birthday +
            ", value='" + value + '\'' +
            '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

}
