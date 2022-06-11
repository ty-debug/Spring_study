package com.zjh.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

public class Person {
    //    定义required为false则该项可以为空
    @Autowired(required = false)
    @Qualifier(value = "cat111")
    private Cat cat;
    @Resource(name = "dog")
    private Dog dog;
    private String name;

    @Override
    public String toString() {
        return "com.zjh.bean.Person{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }

    public Cat getCat() {
        return cat;
    }

    public Dog getDog() {
        return dog;
    }
}
