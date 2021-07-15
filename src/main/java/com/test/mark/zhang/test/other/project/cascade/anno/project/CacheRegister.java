package com.test.mark.zhang.test.other.project.cascade.anno.project;

import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname CacheRegister
 * @Description TODO
 * @Date 2021/7/11 7:01 下午
 * @Created by mark
 */
@Component
public class CacheRegister {
    private final Map<String, List<?>> CACHE_MAP = new ConcurrentHashMap<>();

    public void bind(Object subscriber) {
        List<Method> subscribeMethods = getSubscribeMethods(subscriber);
        subscribeMethods.forEach(m -> tierSubscriber(subscriber, m));
    }
    private void tierSubscriber(Object subscriber, Method method) {
        final CacheContainer mySubscribe = method.getDeclaredAnnotation(CacheContainer.class);
        String key = mySubscribe.value();
        CACHE_MAP.putIfAbsent(key, new ArrayList<>());
//        CACHE_MAP.computeIfAbsent(topic, key -> new ConcurrentLinkedQueue<>());
//        CACHE_MAP.get(topic).add(new MySubscriber(subscriber, method));

    }

    private List<Method> getSubscribeMethods(Object subscriber) {
        final List<Method> result = new ArrayList<>();
        Class<?> clazz = subscriber.getClass();
        while (clazz != null) {
            Method[] declaredMethods = clazz.getDeclaredMethods();
            Arrays.stream(declaredMethods).filter(m -> m.isAnnotationPresent(CacheContainer.class) && m.getParameterCount() == 1 && m.getModifiers() == Modifier.PUBLIC)
                    .forEach(result::add);
        }
        return result;
    }
}
