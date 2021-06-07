package com.test.mark.zhang.test.agency.heima.disign.day6.spring_demo.dao.impl;

import com.test.mark.zhang.test.agency.heima.disign.day6.spring_demo.dao.UserDao;

/**
 * @version v1.0
 * @ClassName: UserDaoImpl
 * @Description: 数据访问层实现类
 * @Author: 黑马程序员
 */
public class UserDaoImpl implements UserDao {

    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDaoImpl() {
        System.out.println("userDao被创建了");
    }

    public void add() {
        System.out.println("UserDao ..." + username + "==" + password);
    }
}
