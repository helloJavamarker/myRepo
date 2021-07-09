package com.test.mark.zhang.test.agency.wang.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.test.mark.zhang.test.agency.wang.guava.eventbus.service.QueryService;
import com.test.mark.zhang.test.agency.wang.guava.eventbus.service.RequestQueryHandler;

/***************************************
 * @author:Alex Wang
 * @Date:2017/10/19
 * 532500648
 ***************************************/
public class ComEachOtherEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        QueryService queryService = new QueryService(eventBus);
        eventBus.register(new RequestQueryHandler(eventBus));
        queryService.query("werwersdf");
    }
}
