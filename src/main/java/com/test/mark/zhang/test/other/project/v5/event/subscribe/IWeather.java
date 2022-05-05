package com.test.mark.zhang.test.other.project.v5.event.subscribe;

public interface IWeather {
    
    void addSubscriber(ISubscriber subscriber);
    
    void delSubscriber(ISubscriber subscriber);
    
    void publishInfo(String msg);
    
}
