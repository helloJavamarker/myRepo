package com.example.security.openapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author by mark
 * @Classname OpenApiConfig
 * @Description TODO
 * @Date 2022/3/29 4:59 下午
 */
@Configuration
public class OpenApiConfig {

    @Autowired
    private ApiKeyService apiKeyService;

    @Bean
    public FilterRegistrationBean openApiRegistration(SecurityProperties securityProperties) {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new OpenApiFIlter(apiKeyService :: valid));
        filterFilterRegistrationBean.addUrlPatterns("/*");
        filterFilterRegistrationBean.setOrder(securityProperties.getFilter().getOrder() - 1);
        return filterFilterRegistrationBean;
    }
}
