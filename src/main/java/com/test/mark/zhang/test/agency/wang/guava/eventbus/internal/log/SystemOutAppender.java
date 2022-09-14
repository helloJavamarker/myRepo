package com.test.mark.zhang.test.agency.wang.guava.eventbus.internal.log;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class SystemOutAppender extends AppenderSkeleton {

    private String prefix;

    @Override
    protected void append(LoggingEvent event) {
        System.out.println("###, " + prefix + " : " + event.getMessage());
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {

        return false;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
