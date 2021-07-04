package com.test.mark.zhang.test.other.project.cascade;

import com.google.common.eventbus.EventBus;

public class EventBusCenter {
  
    private static EventBus eventBus = new EventBus();
  
    private EventBusCenter() {  
  
    }  
  
    public static EventBus getInstance() {  
        return eventBus;  
    }  
  
    public static void register(Object obj) {  
        eventBus.register(obj);  
    }  
  
    public static void unregister(Object obj) {  
        eventBus.unregister(obj);  
    }

    //上面几个方法可以根据项目实际来按需写
  
    public static void post(Object obj) {  
        eventBus.post(obj);  
    }  
 
}  