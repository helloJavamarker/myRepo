package com.test.mark.zhang.test.other.filter;


import org.apache.catalina.connector.RequestFacade;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author by mark
 * @Classname FilterTest1
 * @Description TODO
 * @Date 2021/11/16 3:12 下午
 */
public class FilterTest2 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter22......");
        //filter只会被拦截一次??

        String requestURI = ((RequestFacade) request).getRequestURI();
        if (requestURI.startsWith("/auth/loadPersmission")) {
            System.out.println("doFilter");
            chain.doFilter(request, response);
            // 当有多个filter时,执行顺序    1-3-5-4-2   见雷神ssm第一节
            // 假如不执行doFilter,下面的流程不会执行
        }
        System.out.println("filter22................");
    }
}
