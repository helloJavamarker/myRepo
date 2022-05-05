package com.test.mark.zhang.test.agency.tao.flume;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 从日志内容中区分日志类别，将类别信息插入event的header
 */
public class LogTypeInterceptor implements Interceptor {

    /**
     * 区分类型的标志字段名
     */
    String flagFieldname;

    /**
     * 要添加到header中的标识类别的key名称
     */
    String headerKey;
    Gson gson;

    public LogTypeInterceptor(String flagFieldname, String headerKey) {
        this.flagFieldname = flagFieldname;
        this.headerKey = headerKey;
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

        // 先将日志数据转成hashmap
        Map<String, Object> map = gson.fromJson(lineJson, new TypeToken<HashMap<String, Object>>() {
        }.getType());

        // 判断hashmap中是否存在指定的“标志字段”
        if (map.containsKey(this.flagFieldname)) {
            event.getHeaders().put(this.headerKey, "wxapp_log");
        } else {
            event.getHeaders().put(this.headerKey, "app_log");
        }


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


    public static class LogTypeInterceptorBuilder implements Builder {

        /**
         * 区分类型的标志字段名
         */
        String flagFieldname;

        /**
         * 要添加到header中的标识类别的key名称
         */
        String headerKey;

        /**
         * 构造拦截器实例对象的方法
         *
         * @return
         */
        @Override
        public Interceptor build() {
            return new LogTypeInterceptor(flagFieldname, headerKey);
        }

        /**
         * 从配置文件中获取配置参数的方法
         *
         * @param context
         */
        @Override
        public void configure(Context context) {
            flagFieldname = context.getString("flag.fieldname");
            headerKey = context.getString("headerKey");
        }
    }

}
