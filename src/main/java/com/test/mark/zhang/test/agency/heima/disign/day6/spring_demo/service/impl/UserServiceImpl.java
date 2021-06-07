package com.test.mark.zhang.test.agency.heima.disign.day6.spring_demo.service.impl;

import com.test.mark.zhang.test.agency.heima.disign.day6.spring_demo.dao.UserDao;
import com.test.mark.zhang.test.agency.heima.disign.day6.spring_demo.service.UserService;

/**
 * @version v1.0
 * @ClassName: UserServiceImpl
 * @Description: 业务逻辑层实现类
 * @Author: 黑马程序员
 */
public class UserServiceImpl implements UserService {

    //声明一个UserDao类型的变量
    private UserDao userDao;

    public UserServiceImpl() {
        System.out.println("userService被创建了");
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add() {
        System.out.println("UserService ...");
        userDao.add();
    }
}
