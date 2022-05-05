package com.test.mark.zhang.test.other.project.v5.event.subscribe;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class WeatherServer implements IWeather {

    private static final Logger logger = Logger.getLogger(WeatherServer.class);

    /*用来保存注册了的所有订阅者*/
    private List<ISubscriber> subscribers = new ArrayList<ISubscriber>();

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
        logger.info("a new subscriber is joining");
    }

    @Override
    public void delSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
        logger.info("a subscriber is leaving");
    }

    @Override
    public void publishInfo(String msg) {
        for (ISubscriber subscriber : subscribers) {
            subscriber.todoTomorrow(msg);
        }
        logger.info(String.format("publish a msg: %s ", msg));
    }
}

