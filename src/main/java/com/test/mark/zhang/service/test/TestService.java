package com.test.mark.zhang.service.test;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    static {
        System.out.println(".......");
        System.out.println("testService");
        System.out.println(".......");
    }

    public void sout() {
        System.out.println("hello service");
    }


}
