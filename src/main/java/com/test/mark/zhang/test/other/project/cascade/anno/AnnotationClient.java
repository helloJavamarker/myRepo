package com.test.mark.zhang.test.other.project.cascade.anno;

import org.junit.Test;
import org.springframework.stereotype.Service;

/**
 * @Classname AnnotationClient
 * @Description TODO
 * @Date 2021/7/10 5:32 下午
 * @Created by mark
 */
@Service
public class AnnotationClient {

//    @MyAnnotation(topic = "hh")
    public void testAnno1() {
        System.out.println("method1");
    }

    @MyAnnotation()
    public void testAnno2(String content) {
        System.out.println("method2:" + content);
    }

    @Test
    public void test01() {

    }


}
