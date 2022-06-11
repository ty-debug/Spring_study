package com.zjh.config;

import com.zjh.pojo.User;
import org.springframework.context.annotation.Bean;

public class ZhangConfig {

    @Bean
    public User getUser() {
        return new User();
    }

}
