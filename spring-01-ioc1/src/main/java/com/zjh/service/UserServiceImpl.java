package com.zjh.service;

import com.zjh.dao.UserDao;
import com.zjh.dao.UserDaoImpl;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUserSv() {
        userDao.getUser();
    }
}
