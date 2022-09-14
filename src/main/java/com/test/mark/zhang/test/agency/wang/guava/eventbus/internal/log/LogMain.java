package com.test.mark.zhang.test.agency.wang.guava.eventbus.internal.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import org.apache.log4j.Logger;

/**
 * @author by mark
 * @Classname LogMain
 * @Description TODO
 * @Date 2022/7/20 9:28 上午
 */
//@Slf4j
public class LogMain {
    public static void main(String[] args) {
        Log log = LogFactory.getLog("sysoutLog") ;
        log.error("err");
        log.info("System.out.print.....") ;

        //log4j.properties 配置
        //log4j.logger.sysoutLog=INFO, sysout
        //
        //log4j.appender.sysout=SystemOutAppender
        //log4j.appender.sysout.prefix=@@@
        //
        //
        //执行main函数，输出结果
        //###, @@@ : System.out.print....
    }
}
