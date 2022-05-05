package com.test.mark.zhang.test.agency.tao.flume;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventTimestampInterceptor implements Interceptor {

    String timeStampFiledName;
    String toEncryFieldName;
    String keyName;
    Gson gson;

    public EventTimestampInterceptor(String timeStampFiledName, String toEncryFieldName, String keyName) {
        this.timeStampFiledName = timeStampFiledName;
        this.toEncryFieldName = toEncryFieldName;
        this.keyName = keyName;
    }


    /**
     * 初始化工作所在的方法
     * 在拦截操作之前，会被调用一次
     */
    @Override
    public void initialize() {
        gson = new Gson();
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

        // 将账号字段进行加密
        String s = (String) map.get(toEncryFieldName);
        String s1 = DigestUtils.md5Hex(s);
        map.put(toEncryFieldName, s1);

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
    @Override
    public void close() {

    }


    public static class EventTimestampInterceptorBuilder implements Interceptor.Builder {

        String timeStampFiledName;
        String toEncryFieldName;
        String keyName;

        /**
         * 构造拦截器实例对象的方法
         *
         * @return
         */
        @Override
        public Interceptor build() {
            return new EventTimestampInterceptor(timeStampFiledName, toEncryFieldName, keyName);
        }

        /**
         * 从配置文件中获取配置参数的方法
         *
         * @param context
         */
        @Override
        public void configure(Context context) {
            timeStampFiledName = context.getString("tsFiledName");
            toEncryFieldName = context.getString("toEncryFieldName");
            keyName = context.getString("keyName");
        }
    }

}
