package com.test.mark.zhang.test.other.project.cascade.anno3;

import com.test.mark.zhang.test.other.project.cascade.aware.MyApplicationContextAware;
import org.junit.Test;

/**
 * @author by mark
 * @Classname AwareTest
 * @Description TODO
 * @Date 2021/7/12 6:41 下午
 */
public class AwareTest {

    @Test
    public void test() {
        AwareService bean = MyApplicationContextAware.getBean(AwareService.class);
        System.out.println(bean);
        //null
    }
}
