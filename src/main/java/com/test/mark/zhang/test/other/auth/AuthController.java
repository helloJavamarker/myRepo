package com.test.mark.zhang.test.other.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author by mark
 * @Classname AuthController
 * @Description TODO
 * @Date 2021/11/16 10:07 上午
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/loadPersmission")
    public String loadPermission() throws IOException {
        authService.reloadAuth(null, null);
        return "sucess";
    }

    @GetMapping("/load")
    public String load() throws IOException {
        authService.reloadAuth(null, null);
        System.out.println("invoke method");
        return "sucess";
    }
}
