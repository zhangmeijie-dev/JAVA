package com.spring.di.pojo;

public class Car {

    private String name;
    private String color;


    //销毁方法
    public void destroy(){
        System.out.println(this+"Car的销毁方法");
    }

    //初始方法
    public void init(){
        System.out.println(this+"Car的初始方法");
    }


    public Car() {
        System.out.println("Car的空参构造器");
    }
    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", color='" + color +
                '}';
    }

    public Car(String name, String color) {
        System.out.println("car的满参构造器");
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
