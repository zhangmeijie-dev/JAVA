package com.spring.di.pojo;

import com.spring.ioc.pojo.Person;

import java.io.Serializable;
import java.util.List;

public class P implements Serializable{
    private static final long serialVersionUID = 1L;

    private String nmae;
    private Person user;
    private List list;


    public String getNmae() {
        return nmae;
    }

    public void setNmae(String nmae) {
        this.nmae = nmae;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public P() {
        System.out.println("Pmingchengzhuru的空参构造器");
    }

    public P(String nmae, Person user, List list) {
        System.out.println("Pmingchengzhuru的满参构造器");
        this.nmae = nmae;
        this.user = user;
        this.list = list;
    }

    @Override
    public String toString() {
        return "P{" +
                "nmae='" + nmae + '\'' +
                ", user=" + user +
                ", list=" + list +
                '}';
    }
}
