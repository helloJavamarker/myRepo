package com.test.mark.zhang.test.other.project.v5.event.subscribe;

import org.apache.log4j.Logger;

public class Programmer implements ISubscriber {
    private static final Logger logger = Logger.getLogger(Farmer.class);

    @Override
    public void todoTomorrow(String msg) {

        if ("rain".equals(msg)) {
            logger.info("PROGRAMMER :raining??? Irrelevant !!!");
        } else if ("sunny".equals(msg)) {
            logger.info("PROGRAMMER : sunny Irrelevant !!!");
        } else {
            logger.info("PROGRAMMER : Spam messages");
        }
    }
}

