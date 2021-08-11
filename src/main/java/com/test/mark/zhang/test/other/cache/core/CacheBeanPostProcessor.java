package com.test.mark.zhang.test.other.cache.core;

import com.test.mark.zhang.test.other.cache.annotation.LCache;
import com.test.mark.zhang.test.other.cache.annotation.RCache;
import com.test.mark.zhang.test.other.cache.aspect.CGLibEnhancer;
import com.test.mark.zhang.test.other.cache.aspect.CacheEnhancer;
import com.test.mark.zhang.test.other.cache.aspect.EnhancingFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author bailey
 * @version 1.0
 * @date 2017-06-20 14:01
 */
//自定义适配器（实例化后处理器），仅对User Bean进行处理
@Component
public class CacheBeanPostProcessor implements BeanPostProcessor {
    EnhancingFactory<CacheEnhancer> enhancingFactory = CGLibEnhancer::create;

    ////实例化Bean前调用此方法
    //default Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {


    //在设置某个属性时调用
//    public PropertyValues postProcessPropertyValues(P

    ////实例化Bean后调用此方法
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().getSimpleName().contains("RoleI")) {
            System.out.println(bean.getClass());
            System.out.println("..........");
        }
        for (Method m : bean.getClass().getMethods()) {
            if (m.getName().contains("ByIdPro")) {
                System.out.println(1);
            }
            RCache rc = m.getDeclaredAnnotation(RCache.class);
            if (rc == null) {
                LCache lc = m.getDeclaredAnnotation(LCache.class);
                if (lc == null) {
                    continue;
                }
            }
            try {
                Object enhancedBean = enhancingFactory.create(bean).enhance();
                //BeanCopierUtils.copyParentAttribute(bean, enhancedBean);
                BeanUtils.copyProperties(bean, enhancedBean);
                return enhancedBean;
            } catch (Exception e) {
                throw new FatalBeanException("create enhanced cache bean error :" + beanName, e);
            }
        }
        return bean;
    }
}
