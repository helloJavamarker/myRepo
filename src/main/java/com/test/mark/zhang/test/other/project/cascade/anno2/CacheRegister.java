package com.test.mark.zhang.test.other.project.cascade.anno2;

import com.test.mark.zhang.test.agency.wang.guava.eventbus.internal.MySubscribe;
import com.test.mark.zhang.test.agency.wang.guava.eventbus.internal.MySubscriber;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author by mark
 * @Classname CacheRegister
 * @Description TODO
 * @Date 2021/7/12 4:42 下午
 */
public class CacheRegister {
    private static final Map<String, List<Object>> CACHE_MAP = new ConcurrentHashMap<>();

    public void bind(Object subscriber) {
        List<Method> subscribeMethods = getSubscribeMethods(subscriber);
        subscribeMethods.forEach(m -> tierSubscriber(subscriber, m));
    }

    private void tierSubscriber(Object subscriber, Method method) {
        final Cacheable mySubscribe = method.getDeclaredAnnotation(Cacheable.class);
        String cacheKey = mySubscribe.value();
        CACHE_MAP.computeIfAbsent(cacheKey, key -> new ArrayList<>());
        CACHE_MAP.get(cacheKey).add(new MySubscriber(subscriber, method));

    }

    private List<Method> getSubscribeMethods(Object subscriber) {
        final List<Method> methods = new ArrayList<>();
        Class<?> temp = subscriber.getClass();
        while (temp != null) {
            Method[] declaredMethods = temp.getDeclaredMethods();
            Arrays.stream(declaredMethods)
                    .filter(m -> m.isAnnotationPresent(Cacheable.class) && m.getParameterCount() == 1 && m.getModifiers() == Modifier.PUBLIC)
                    .forEach(methods::add);
            temp = temp.getSuperclass();
        }
        return methods;
    }

}
