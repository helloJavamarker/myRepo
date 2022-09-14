package com.test.mark.zhang.test.other.project.log4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Test;

import java.util.Date;

/**
 * @author by mark
 * @Classname KafkaLog4jAppender
 * @Description TODO
 * @Date 2022/7/8 10:49 上午
 */
//@Slf4j
// 10:56:24.143 [main] INFO com.test.mark.zhang.test.other.project.log4j.KafkaLog4jAppender - ni hao
public class KafkaLog4jAppender extends AppenderSkeleton {

    private static final String PREFIX = "msg :";
    //    Log log = LogFactory.getLog("sysoutLog") ;
//10:57:00.031 [main] INFO sysoutLog - ni hao
    Log log = LogFactory.getLog(KafkaLog4jAppender.class);
    //11:03:27.996 [main] INFO com.test.mark.zhang.test.other.project.log4j.KafkaLog4jAppender - ni hao

    @Test
    public void testAppend() {
        System.out.println("...");
        log.info("ni hao");
    }

    @Override
    protected void append(LoggingEvent loggingEvent) {
        System.out.println("###, " + PREFIX + " : " + loggingEvent.getMessage());
        LogLog.debug("[" + new Date(loggingEvent.getTimeStamp()) + "]" + loggingEvent.getMessage());

    }

    @Override
    public void close() {
        if (!this.closed) {
            this.closed = true;
            //producer.close();
        }
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}
