package com.test.mark.zhang.test.agency.tao.flume;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EncryptSpecifiedFieldInterceptor implements Interceptor {

    String toEncryFieldName;
    Gson gson;

    public EncryptSpecifiedFieldInterceptor(String toEncryFieldName) {
        this.toEncryFieldName = toEncryFieldName;
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

        // 将日志数据转为hashmap
        Map<String, Object> map = gson.fromJson(lineJson, new TypeToken<HashMap<String, Object>>() {
        }.getType());


        // 从hashmap中取出指定要加密的字段
        String s = (String) map.get(toEncryFieldName);

        // 对字段值进行加密
        String s1 = DigestUtils.md5Hex(s);

        // 将加密后的结果替换掉map中原始的值
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


    public static class EncryptInterceptorBuilder implements Builder {

        String toEncryFieldName;

        /**
         * 构造拦截器实例对象的方法
         *
         * @return
         */
        @Override
        public Interceptor build() {
            return new EncryptSpecifiedFieldInterceptor(toEncryFieldName);
        }

        /**
         * 从配置文件中获取配置参数的方法
         *
         * @param context
         */
        @Override
        public void configure(Context context) {
            toEncryFieldName = context.getString("toEncryFieldName");
        }
    }

}
