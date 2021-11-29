package com.example.security.conf;
import java.util.Collection;
import javax.servlet.DispatcherType;
import java.util.EnumSet;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;

/**
 * @author by mark
 * @Classname WebConfigAdapterBean
 * @Description TODO
 * @Date 2021/11/18 9:18 上午
 */
@Configuration
public class WebConfigAdapterBean extends WebMvcConfigurerAdapter {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(SecurityProperties securityProperties) {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        //filterFilterRegistrationBean.setFilter();
        return null;
    }
}
