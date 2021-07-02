package com.test.mark.zhang.test.other.project.security;

import org.springframework.stereotype.Service;

/**
 * @Classname SecurityZoneService
 * @Description TODO
 * @Date 2021/6/25 3:21 下午
 * @Created by mark
 */
@Service
public class SecurityZoneService {
    public String add () {
        System.out.println("add");
        return "add success";
    }
}
