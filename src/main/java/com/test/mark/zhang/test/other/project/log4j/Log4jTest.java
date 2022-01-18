package com.test.mark.zhang.test.other.project.log4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author by mark
 * @Classname Log4jTest
 * @Description TODO
 * @Date 2021/12/11 10:49 下午
 */
public class Log4jTest {
    private static final Logger LOGGER = LogManager.getLogger(Log4jTest.class);

    public static void main(String[] args) {
        String username = "${java:vm}";
//        LOGGER.info("hello: {}!",username);
    }
}
