package com.example.security.service.impl;

import com.example.security.entity.Role;
import com.example.security.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by mark
 * @Classname UserServiceImpl
 * @Description TODO
 * @Date 2021/11/18 9:48 上午
 */

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<Role> loadRole() {
        //get from db
        return null;
    }

    @Override
    public Role getOneByName(String name) {
        //get from db
        return null;
    }
}
