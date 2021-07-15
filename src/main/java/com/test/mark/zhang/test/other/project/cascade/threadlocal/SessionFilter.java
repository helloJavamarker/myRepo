package com.test.mark.zhang.test.other.project.cascade.threadlocal;

import com.test.mark.zhang.test.other.project.cascade.session.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author by mark
 * @Classname A
 * @Description TODO
 * @Date 2021/7/14 2:33 下午
 */
public class SessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        User userSession = (User)request.getSession().getAttribute("userInfo");
        if (userSession != null) {
            //先销毁在添加否则触发不了监听器中的attributeAdded
            request.getSession().removeAttribute("userInfo");
            //重新设值session
            request.getSession().setAttribute("userInfo", userSession);
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}

