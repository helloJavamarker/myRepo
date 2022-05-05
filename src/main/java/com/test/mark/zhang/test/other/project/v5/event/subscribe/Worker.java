package com.test.mark.zhang.test.other.project.v5.event.subscribe;

import org.apache.log4j.Logger;

public class Worker implements ISubscriber {
    private static final Logger logger = Logger.getLogger(Farmer.class);

    @Override
    public void todoTomorrow(String msg) {

        if ("rain".equals(msg)) {
            logger.info("WORKER : a satisfied day!!!");
        } else if ("sunny".equals(msg)) {
            logger.info("WORKER : a terrible day!!!");
        } else {
            logger.info("WORKER : Spam messages");
        }
    }
}

