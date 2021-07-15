package com.test.mark.zhang.test.other.project.springevent;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author by mark
 * @Classname MySpringEvent
 * @Description TODO
 * @Date 2021/7/12 7:32 下午
 */
@Service
public class MySpringEvent {

    @Resource
    private ApplicationEventPublisher publisher;
    //todo

}
