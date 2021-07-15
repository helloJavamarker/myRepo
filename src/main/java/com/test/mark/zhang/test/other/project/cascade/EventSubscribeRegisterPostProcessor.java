package com.test.mark.zhang.test.other.project.cascade;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.eventbus.Subscribe;
import com.google.common.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @Classname EventSubscribeRegisterPostProcessor
 * @Description  这里假如maven依赖关系是A依赖B,在A中定义了一个eventBus,在B中不能直接注册,可以通过这种方式来实现注册,而且不用显式注册
 *  使用中间件可以让两个完全独立的系统间解耦,使用中间件可以让同一个系统中的不同模块间解耦
 * @Date 2021/7/9 1:33 下午
 * @Created by mark
 */
@Component
@Slf4j
public class EventSubscribeRegisterPostProcessor implements InstantiationAwareBeanPostProcessor {

    private static final Cache<String, Object> subscriberBeanCache = CacheBuilder.newBuilder().weakKeys().build();

    private static final LoadingCache<Class<?>, ImmutableList<Method>> subscriberMethodCache =
            CacheBuilder.newBuilder()
            .weakKeys()
            .build(new CacheLoader<Class<?>, ImmutableList<Method>>() {
                @Override
                public ImmutableList<Method> load(Class<?> concreteClass) throws Exception {
                    return getAnnotateMethodsInternal(concreteClass);
                }
            });

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)  throws BeansException {
        //注册订阅者
        subscribeRegister(bean, beanName);
        return bean;
    }

    private void subscribeRegister(Object bean, String beanName) {
        Object exitedSubscribe = subscriberBeanCache.getIfPresent(beanName);
        if (!ObjectUtils.isEmpty(exitedSubscribe)) {
            log.debug("subscriber has bean registered [{}]",beanName);
            return;
        }
        ImmutableList<Method> uncheckedMethod = subscriberMethodCache.getUnchecked(bean.getClass());
        if (!CollectionUtils.isEmpty(uncheckedMethod)) {
            CascadeEventPublish.eventBus.register(bean);
            subscriberBeanCache.put(beanName,bean);
        }
    }


    private static ImmutableList<Method> getAnnotateMethodsInternal(Class<?> clazz) {
        Set<? extends Class<?>> supers = TypeToken.of(clazz).getTypes().rawTypes();
        Map<MethodIdentifier, Method> identifiers = Maps.newHashMap();
        for (Class<?> superClazz : supers) {
            for (Method superClazzMethod : superClazz.getMethods()) {
                if (AnnotationUtils.findAnnotation(superClazzMethod, Subscribe.class) != null) {
                    Class<?>[] parameterTypes = superClazzMethod.getParameterTypes();
                    if (parameterTypes.length != 1) {
                        continue;
                    }
                    MethodIdentifier iden = new MethodIdentifier(superClazzMethod);
                    if (!identifiers.containsKey(iden)) {
                        identifiers.put(iden, superClazzMethod);
                    }
                }
            }
        }
        return ImmutableList.copyOf(identifiers.values());
    }


    private static final class MethodIdentifier {
        private final String name;
        private final List<Class<?>> parameterTypes;

        //私有构造
        private MethodIdentifier(Method method) {
            this.name = method.getName();
            this.parameterTypes = Arrays.asList(method.getParameterTypes());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MethodIdentifier that = (MethodIdentifier) o;
            return Objects.equals(name, that.name) && Objects.equals(parameterTypes, that.parameterTypes);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, parameterTypes);
        }
    }
}
