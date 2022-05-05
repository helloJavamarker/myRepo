package com.test.mark.zhang.test.other.project.v5.event.subscribe;

import org.apache.log4j.Logger;

// 扩展增加实现类
public class Farmer implements ISubscriber {
    private static final Logger logger = Logger.getLogger(Farmer.class);

    @Override
    public void todoTomorrow(String msg) {

        if ("rain".equals(msg)) {
            logger.info("FARMER : a wonderful day!!!");
        } else if ("sunny".equals(msg)) {
            logger.info("FARMER : a enrich day!!!");
        } else {
            logger.info("FARMER : Spam messages");
        }

        // 扩展要改代码
    }
}

