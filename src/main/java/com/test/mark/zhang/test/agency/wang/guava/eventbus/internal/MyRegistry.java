package com.test.mark.zhang.test.agency.wang.guava.eventbus.internal;

/***************************************
 * @author:Alex Wang
 * @Date:2017/10/21
 * 532500648
 ***************************************/

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * <pre>
 * topic1->subscriber-subscribe
 *       ->subscriber-subscribe
 *       ->subscriber-subscribe
 * topic2->subscriber-subscribe
 *       ->subscriber-subscribe
 *       ->subscriber-subscribe
 *
 * </pre>
 */
//default class表示在包内可见
class MyRegistry {

    // 这个容器存的是啥   key是topic??
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<MySubscriber>> subscriberContainer
            = new ConcurrentHashMap<>();

    public void bind(Object subscriber) {
        List<Method> subscribeMethods = getSubscribeMethods(subscriber);
        subscribeMethods.forEach(m -> tierSubscriber(subscriber, m));
    }


    public void unbind(Object subscriber) {

        subscriberContainer.forEach((key, queue) ->
                queue.forEach(s -> {
                    if (s.getSubscribeObject() == subscriber) {
                        s.setDisable(true);
                    }
                }));
    }

    public ConcurrentLinkedQueue<MySubscriber> scanSubscriber(final String topic) {
        return subscriberContainer.get(topic);
    }

    private void tierSubscriber(Object subscriber, Method method) {
        final MySubscribe mySubscribe = method.getDeclaredAnnotation(MySubscribe.class);
        String topic = mySubscribe.topic();
        subscriberContainer.computeIfAbsent(topic, key -> new ConcurrentLinkedQueue<>());
        // 某个对象的某个方法
        subscriberContainer.get(topic).add(new MySubscriber(subscriber, method));

    }

    //subscriber ??
    private List<Method> getSubscribeMethods(Object subscriber) {
        final List<Method> methods = new ArrayList<>();

        //??
        Class<?> temp = subscriber.getClass();
        while (temp != null) {
            Method[] declaredMethods = temp.getDeclaredMethods();
            Arrays.stream(declaredMethods)
                    .filter(m -> m.isAnnotationPresent(MySubscribe.class) && m.getParameterCount() == 1 && m.getModifiers() == Modifier.PUBLIC)
                    .forEach(methods::add);
            temp = temp.getSuperclass();
        }
        return methods;
    }
}
