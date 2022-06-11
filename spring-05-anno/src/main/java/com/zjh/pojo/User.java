package com.zjh.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//等价于标签bean的装配
@Component
@Scope("singleton")
public class User {

    public String name;

    @Value("zjh111")
    public void setName(String name) {
        this.name = name;
    }
}
