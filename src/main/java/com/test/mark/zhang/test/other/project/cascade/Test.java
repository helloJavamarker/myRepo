package com.test.mark.zhang.test.other.project.cascade;

public class Test {
  
    public static void main(String[] args) throws InterruptedException {  
  
        DataObserver1 observer1 = new DataObserver1();  
        DataObserver2 observer2 = new DataObserver2();  
  
        EventBusCenter.register(observer1);  
        EventBusCenter.register(observer2);  
  
        System.out.println("============   start  ====================");  
  
        // 只有注册的参数类型为String的方法会被调用  
        EventBusCenter.post("post string method");  
        EventBusCenter.post(123);  
  
        System.out.println("============ after unregister ============");  
        // 注销observer2  
        EventBusCenter.unregister(observer2);  
        EventBusCenter.post("post string method");  
        EventBusCenter.post(123);
//       " kafka_list=10.255.97.5:9093,10.255.97.4:9093,10.255.97.143:9093,10.255.97.144:9093,10.255.97.145.9093,10.255.97.146:9093,10.255.97.147:9093,10.172.16.240.79:9093,10.255.97.148:9093"
//
//        "kafka_list2=10.255.82.32:29092,10.255.82.31:29092,10.255.82.80:29092,10.255.82.5:29092,10.255.82.4:29092,10.255.82.33:29092,10.255.82.42:29092,10.255.82.41:29092,10.255.82.72:29092,10.255.82.61:29092"
        System.out.println("============    end           =============");  
    }  
}  