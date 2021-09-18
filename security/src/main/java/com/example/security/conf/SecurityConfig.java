package com.example.security.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author by mark
 * @Classname SecurityConfig
 * @Description TODO
 * @Date 2021/9/10 1:52 下午
 */
@Configuration
//@EnableWebSecurity  这个为什么没有加
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("mark")
                .password("mark").roles("admin");
        //多个用户可以用and()相连
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**","/images/**");
        //忽略的文件,一般静态文件可以忽略    项目里面error,/openapi/v1.0/auth/apikey页面也忽略了
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("name")
                .passwordParameter("passwd")
                .defaultSuccessUrl("/index")
                .successForwardUrl("/index")
                .permitAll()
                //.loginProcessingUrl("/doLogin")   //用来指定登录接口
                .loginPage("/login.html")  //注意要写好login.html,不然会报错   指定登录页面
                .permitAll()    //permitAll 表示登录相关的页面/接口不要被拦截。
                .and()
                .csrf().disable();

        //GET http://localhost:8080/login.html
        //POST http://localhost:8080/login.html
        //前面的 GET 请求用来获取登录页面，后面的 POST 请求用来提交登录数据。

        //实际操作中，defaultSuccessUrl 和 successForwardUrl 只需要配置一个即可

        //在 Spring Security 中有一个非常重要的对象叫做 Authentication，我们可以在任何地方注入 Authentication 进而获取到当前登录用户信息，
        // Authentication 本身是一个接口，它实际上对 java.security.Principal 做的进一步封装
    }
}
