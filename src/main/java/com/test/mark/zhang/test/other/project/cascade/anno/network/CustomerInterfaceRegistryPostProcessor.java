package com.test.mark.zhang.test.other.project.cascade.anno.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public class CustomerInterfaceRegistryPostProcessor implements PriorityOrdered, BeanDefinitionRegistryPostProcessor {
    private final static Logger LOG = LoggerFactory.getLogger(CustomerInterfaceRegistryPostProcessor.class);
 
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
    
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("postProcessBeanFactory");
        }
        
        //搜索被Interface注解的类。key为bean的id，value为bean的实例。
        //beanFactory.getBeansWithAnnotation返回时通过getBean方法将bean的实例化，如果bean中autowired注解的属性没有实例化就会注入null
        //Map<String, Object> map = beanFactory.getBeansWithAnnotation(Interface.class);
        String[] ary = beanFactory.getBeanNamesForAnnotation(Interface.class);
        if (ary!=null && ary.length>0) {
            for (String beanName : ary) {                
                //通过Spring的beanName获取bean的类型
                Class<?> cls = beanFactory.getType(beanName);
                if (cls.getAnnotations()!=null && cls.getAnnotations().length>0) {
                    for (Annotation annotation : cls.getAnnotations()) {
                        if (annotation instanceof Interface) {
                            Interface intfc = (Interface) annotation;
                            
                            //将全限定名注册为别名
                            this.registerAlias(beanFactory, beanName, cls.getName());
                            
                            //其他别名注册
//                            for (String row : intfc.value()) {
//                                this.registerAlias(beanFactory, beanName, row);
//                            }
                        }
                    }
                }
            }
        }
    }
 
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("postProcessBeanDefinitionRegistry");
        }
    }
    
    /**
     * 为bean注册别名
     * 注意：如果别名与bean的ID冲突，放弃别名注册
     * @param factory ConfigurableListableBeanFactory
     * @param beanId bean的ID
     * @param value Interface的value
     */
    private void registerAlias(ConfigurableListableBeanFactory factory, String beanId, String value) {
        //防止别名覆盖bean的ID
        if (factory.containsBeanDefinition(value)) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[failed] because value=" + value + " is existed");
            }
            return;
        }
        else {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[success] beanId=" + beanId + ", value=" + value);
            }
            factory.registerAlias(beanId, value);
        }
    }
}