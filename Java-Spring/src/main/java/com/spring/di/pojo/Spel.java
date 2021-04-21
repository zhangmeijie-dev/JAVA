package com.spring.di.pojo;

import java.io.Serializable;

public class Spel implements Serializable{
    private static final long serialVersionUID = 1L;

    private String name;
    private String id;
    private String address;
    private Integer age;
    private Car car;

    @Override
    public String toString() {
        return "Spel{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", car=" + car +
                '}';
    }

    public Spel(String name, String id, String address, Integer age, Car car) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.age = age;
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Spel() {
    }
}
