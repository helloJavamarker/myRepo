package com.example.security.conf;

import cn.hutool.core.util.NumberUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author by mark
 * @Classname WebSecurityConfig
 * @Description TODO
 * @Date 2021/11/18 9:18 上午
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }

    private AccessDecisionManager accessDecisionManager() {

        return null;
    }

    public static void main(String[] args) {
//        String num = "57694678028910X";
        String num = "354420668";
//        String num = "168314752";
//        String num2 = "1.342445333332222";
        System.out.println(Double.parseDouble(num));
        System.out.println((Double.parseDouble(num) / 1024));
        System.out.println((Double.parseDouble(num) / 1024 / 1024));
        System.out.println((Double.parseDouble(num) / 1024 /1024 /1024));
        System.out.println(NumberUtil.div(Double.parseDouble(num), 1024  * 1024  * 1024, 2));
//        System.out.println(Double.parseDouble(num2));
    }



}
