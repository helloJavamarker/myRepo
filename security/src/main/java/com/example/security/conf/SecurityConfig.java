package com.example.security.conf;

import com.example.security.util.AttrRequestMatcher;
import com.example.security.util.NillAuth;
import com.example.security.voter.AllowHostVoter;
import com.example.security.voter.AllowMethodVoter;
import com.example.security.voter.AllowRefererVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author by mark
 * @Classname SecurityConfig
 * @Description TODO
 * @Date 2021/9/10 1:52 下午
 */
@Configuration
//@EnableWebSecurity  这个为什么没有加
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //最好弄成配置
    private static final String FILTER_IGNORE = ".pdf,.gif,.jpg,.png,.js,.html,.css,.xlsl,.rar,.zip,.xls,.doc,.txt,.docx";

    private RequestMatcher allowRequest = new AttrRequestMatcher("permitType");

    @Value("${host.master}")
    private String masterHost;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @Bean
    public UserDetailsService customUserService() {
        return new CustomUserService();
    }

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

        auth.userDetailsService(customUserService())
                .passwordEncoder(NoOpPasswordEncoder.getInstance());

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**","/images/**");
        //忽略的文件,一般静态文件可以忽略    项目里面error,/openapi/v1.0/auth/apikey页面也忽略了

        String[] filterIgnore = FILTER_IGNORE.split(",");
        for (int i = 0; i < filterIgnore.length; i++) {
            filterIgnore[i] = "/**/*" + filterIgnore[i];
        }
        web.ignoring().regexMatchers()
                .antMatchers(filterIgnore)
                .antMatchers("/", "/static/**", "error", "/aicso/login");
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

        http.authorizeRequests()
                .accessDecisionManager(accessDecissionManager())
                .requestMatchers(whitePermissions()).permitAll();

    }

    /**
     * 鉴权投票器
     * @see AndVoter :同时满足method,referer,host和自带投票器才通过
     * @see AllowHostVoter:
     * @see AllowRefererVoter 请求referer不在trustReferer中,直接拒绝
     * @see AllowMethodVoter 限制http方法,过滤不安全的method
     * @see WebExpressionVoter security自带投票器
     * @return
     */
    private AccessDecisionManager accessDecissionManager() {
        return new CustomUnanimousBased(Arrays.asList(new AllowHostVoter(allowRequest, masterHost),
                new AllowRefererVoter(masterHost, allowRequest)));
    }

    private RequestMatcher[] whitePermissions() {
        List<RequestMatcher> whiteList = new ArrayList<>();
        Map<RequestMappingInfo, HandlerMethod> handlerMap = handlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> handle : handlerMap.entrySet()) {
            RequestMappingInfo info = handle.getKey();
            HandlerMethod method = handle.getValue();
            if (method.getBeanType().getAnnotation(NillAuth.class) != null
                || method.hasMethodAnnotation(NillAuth.class)) {
                addAllMatcher(whiteList, info);
            }
        }
        whiteList.add(allowRequest);
        return whiteList.toArray(new RequestMatcher[0]);
    }

    private void addAllMatcher(List<RequestMatcher> list, RequestMappingInfo info) {
        Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
        Set<String> patterns = info.getPatternsCondition().getPatterns();
        if (CollectionUtils.isEmpty(methods)) {
            for (String pattern : patterns) {
                list.add(new AntPathRequestMatcher(pattern, null));
            }
        } else {
            for (String pattern : patterns) {
                for (RequestMethod method : methods) {
                    list.add(new AntPathRequestMatcher(pattern, method.toString()));
                }
            }
        }
    }
}
