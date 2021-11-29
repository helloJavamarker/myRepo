package com.example.security.service;

import com.example.security.entity.Role;

import java.util.List;

/**
 * @author by mark
 * @Classname UserService
 * @Description TODO
 * @Date 2021/11/18 9:48 上午
 */
public interface UserService {

    List<Role> loadRole();

    Role getOneByName(String name);
}
