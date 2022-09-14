package com.example.security.filter;

import com.example.security.controller.LoginController;
import com.example.security.entity.User;
import com.example.security.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jasig.cas.client.util.AssertionHolder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author by mark
 * @Classname CasFilter
 * @Description TODO
 * @Date 2022/7/7 2:08 下午
 */
@AllArgsConstructor
@Data
@Slf4j
public class CasFilter implements Filter {
    private LoginController loginController;
    private UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        try {
            Object user = httpRequest.getSession().getAttribute("userInfo");
            if (user == null && AssertionHolder.getAssertion() != null && AssertionHolder.getAssertion().isValid()) {
                String username = AssertionHolder.getAssertion().getPrincipal().getName();
//                String loginIp = OtherUtil.getIpAAddr(httpReequest);
                String loginIp = "127.0.0.1";
                User loginUser = userService.login(loginIp, username);
                if (loginUser == null) {
                    httpResponse.setStatus(400);
                    httpResponse.setContentType("application/json; charset=UTF-8");
                    httpResponse.getOutputStream().write("{未查询到用户信息,请先登录}".getBytes(StandardCharsets.UTF_8));
                    return;
                }
//               JsonObject loginController.packageSession(loginUser, httpRequest.getSession());
//                loginController.setSecuritySession(httpRequest.getSession(),JsonObject)
                httpResponse.sendRedirect("index.html");
            }
        } catch (Exception e) {
            log.error("单点登录失败:[{}]", e.getMessage(), e);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
