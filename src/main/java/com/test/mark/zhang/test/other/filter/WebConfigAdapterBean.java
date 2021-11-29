package com.test.mark.zhang.test.other.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author by mark
 * @Classname WebConfigAdapterBean
 * @Description TODO
 * @Date 2021/11/16 3:27 下午
 */
@Configuration
public class WebConfigAdapterBean {

    @Bean
    public FilterRegistrationBean filterTest2() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new FilterTest2());
        bean.addUrlPatterns("/auth/*");
        bean.setName("test2");
        bean.setOrder(10);
        return bean;
    }

    @Bean
    public FilterRegistrationBean filterTest1() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new FilterTest1());
        bean.addUrlPatterns("/auth/loadPersmission");
        bean.setName("test1");
        bean.setOrder(10);
        return bean;
    }
}
