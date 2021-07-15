package com.test.mark.zhang.test.other.project.cascade.anno.project;

import com.test.mark.zhang.test.other.project.org.Organization;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname CacheHandler
 * @Description TODO
 * @Date 2021/7/11 3:26 下午
 * @Created by mark
 */
@Component
public final class CacheHandler implements InstantiationAwareBeanPostProcessor {
    private static final Map<String, ?> CACHE_MAP = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() throws Exception {
        initData();
    }

    private List<Organization> initData() throws ClassNotFoundException {
        /*CacheContainer cacheContainer = AnnotationUtils.findAnnotation(MyController.class, CacheContainer.class);
        if (cacheContainer != null) {
            //Class<?> clazz = Class.forName("com.test.mark.zhang.test.other.project.cascade.anno.project.MyController");
            //annotation.
            //CacheContainer annotation = clazz.getAnnotation(cacheContainer.getClass());
            System.out.println("cacheContainer::::::"+ cacheContainer);
        }*/
        Class<?> clazz = Class.forName("com.test.mark.zhang.test.other.project.cascade.anno.project.MyController");
        CacheContainer annotation = clazz.getAnnotation(CacheContainer.class);
        for (Annotation clazzAnnotation : clazz.getAnnotations()) {
            System.out.println("clazzAnnotation ,,,,," + clazzAnnotation);
            //这里自定义并没有被扫描
        }
        System.out.println("annotation........"+ annotation);
        return null;
    }


}
