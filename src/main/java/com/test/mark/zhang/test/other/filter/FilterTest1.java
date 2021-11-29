package com.test.mark.zhang.test.other.filter;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author by mark
 * @Classname FilterTest1
 * @Description TODO
 * @Date 2021/11/16 3:12 下午
 */
public class FilterTest1 implements Filter {

    /**
     * 这是Tomcat里面的filter
     * init() ：该方法在容器启动初始化过滤器时被调用，它在 Filter 的整个生命周期只会被调用一次。注意：这个方法必须执行成功，否则过滤器会不起作用。
     * doFilter() ：容器中的每一次请求都会调用该方法， FilterChain 用来调用下一个过滤器 Filter。
     * destroy()： 当容器销毁 过滤器实例时调用该方法，一般在方法中销毁或关闭资源，在过滤器 Filter 的整个生命周期也只会被调用一次
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 注意,这里bean开始并没有被spring管理,所以,在没有加入spring容器前,并不会过滤接口
        System.out.println("filter......");
        chain.doFilter(request, response);
        System.out.println("filter................");
    }
}
