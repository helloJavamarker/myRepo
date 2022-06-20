package com.test.mark.zhang.test.agency.heima.man.interview.day04.tx.app.controller;

import com.test.mark.zhang.test.agency.heima.man.interview.day04.tx.app.service.Service5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.FileNotFoundException;

@Controller
public class AccountController {

    @Autowired
    public Service5 service;

    public void transfer(int from, int to, int amount) throws FileNotFoundException {
        service.transfer(from, to, amount);
    }
}
