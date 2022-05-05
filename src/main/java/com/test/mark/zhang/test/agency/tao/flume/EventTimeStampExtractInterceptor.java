package com.test.mark.zhang.test.agency.tao.flume;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventTimeStampExtractInterceptor implements Interceptor {

    String timeStampFiledName;
    String keyName;
    Gson gson;

    public EventTimeStampExtractInterceptor(String timeStampFiledName, String keyName) {
        this.timeStampFiledName = timeStampFiledName;
        this.keyName = keyName;
    }


    /**
     * 初始化工作所在的方法
     * 在拦截操作之前，会被调用一次
     */
    @Override
    public void initialize() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        gson = gsonBuilder.create();
    }

    /**
     * 拦截操作的具体逻辑所在方法
     *
     * @param event
     * @return
     */
    @Override
    public Event intercept(Event event) {

        byte[] body = event.getBody();
        String lineJson = new String(body);

        // 获取时间戳，放入header
        Map<String, Object> map = gson.fromJson(lineJson, new TypeToken<HashMap<String, Object>>() {
        }.getType());
        Double ts = (Double) map.get(timeStampFiledName);
        event.getHeaders().put(keyName, ts.longValue() + "");

        map.put("timeStamp", ts.longValue());


        // 将加密处理后的日志内容（map中），重新恢复成json，并set到event的body中
        String newJson = gson.toJson(map);


        event.setBody(newJson.getBytes());


        return event;
    }

    @Override
    public List<Event> intercept(List<Event> list) {

        for (Event event : list) {
            intercept(event);
        }
        return list;
    }


    /**
     * agent关闭时，会调用该方法来做一些清理工作
     */
    public void close() {

    }


    public static class EventTimestampInterceptorBuilder implements Builder {

        String timeStampFiledName;
        String keyName;

        /**
         * 构造拦截器实例对象的方法
         *
         * @return
         */
        @Override
        public Interceptor build() {
            return new EventTimeStampExtractInterceptor(timeStampFiledName, keyName);
        }

        /**
         * 从配置文件中获取配置参数的方法
         *
         * @param context
         */
        @Override
        public void configure(Context context) {
            timeStampFiledName = context.getString("tsFiledName");
            keyName = context.getString("keyName");

        }
    }

}
