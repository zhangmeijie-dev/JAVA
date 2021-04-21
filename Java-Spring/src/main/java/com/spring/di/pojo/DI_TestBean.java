package com.spring.di.pojo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DI_TestBean {
    private Object[] arr;
    private List list;
    private Map map;
    private Car car;

    public DI_TestBean(Object[] arr, List list, Map map, Car car) {
        this.arr = arr;
        this.list = list;
        this.map = map;
        this.car = car;
    }

    public DI_TestBean() {
    }

    public Object[] getArr() {
        return arr;
    }

    public void setArr(Object[] arr) {
        this.arr = arr;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Map getMap() {
        return map;
    }
    public void setMap(Map map) {
        this.map = map;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
    @Override
    public String toString() {
        return "DI_TestBean{" +
            "arr=" + Arrays.toString(arr) +
            ", list=" + list +
            ", map=" + map +
            ", car=" + car +
            '}';
    }

}
