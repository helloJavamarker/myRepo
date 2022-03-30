package com.example.security.openapi;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author by mark
 * @Classname OpenApiFIlter
 * @Description TODO
 * @Date 2022/3/29 4:07 下午
 */
@Slf4j
public class OpenApiFIlter implements Filter {

    // trustIp  allowMethod
    private static final String APIKEY = "apiKey";
    private static final String PERMIT_TYPE = "permitType";
    private static final RequestMatcher OPENAPI_MATCHER = new AntPathRequestMatcher("/openapi/**");
    private static final List<GrantedAuthority> PERMIT_ALL = Arrays.asList(new SimpleGrantedAuthority("permitAll"));
    private Predicate<String> apiKeyValid;

    public OpenApiFIlter(Predicate<String> apiKeyValid) {
        this.apiKeyValid = apiKeyValid;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 历史apikey,兼容老代码
        String apiKey = request.getParameter(APIKEY);
        if (StringUtils.isNotBlank(apiKey)) {
            filterThen(validOldKey(apiKey), apiKey, request, response, filterChain);
            return;
        }
        // apikey 仅对openapi开头的接口有效
        apiKey = request.getHeader(APIKEY);
        if (OPENAPI_MATCHER.matcher(request).isMatch() && StringUtils.isNotBlank(apiKey)) {
            long startTime = System.currentTimeMillis();
            filterThen(validOldKey(apiKey), apiKey, request, response, filterChain);
            long endTime = System.currentTimeMillis();
            Enumeration<String> headerNames = request.getHeaderNames();
            StringBuffer requestHeader = new StringBuffer();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String header = request.getHeader(headerName);
                requestHeader.append(headerName);
                requestHeader.append(":");
                requestHeader.append(header);
                requestHeader.append("/");
            }
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private void filterThen(Pair<Boolean, String> validResult, String apiKey, HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        //apikey校验通过,直接免密通过
        if (validResult.getKey()) {
            request.setAttribute(PERMIT_TYPE, APIKEY);
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                Authentication securityContext = AuthUtil.getSecurityContext(request);
                if (securityContext == null) {
                    securityContext = new AnonymousAuthenticationToken(apiKey, validResult.getValue(), PERMIT_ALL);
                }
                SecurityContextHolder.getContext().setAuthentication(securityContext);
            }
            request = new SecurityContextHolderAwareRequestWrapper(request, "ROLE_");
            chain.doFilter(request, response);
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print("{\"error\":\"无效的apiKey\"}");
        }
    }

    private Pair<Boolean, String> validOldKey(String apiKey) {
        try {
            String jasyptDecrypt = SecretUtil.jasyptDecrypt(apiKey, SecretUtil.HMAC_SHA1);
            String systemApiKey = "apiKeyByDelin";
            if (systemApiKey.equals(jasyptDecrypt)) {
                return Pair.of(true, null);
            } else {
                return Pair.of(false, null);
            }
        } catch (Exception e) {
            log.error("apiKey解析失败:", e);
            return Pair.of(false, null);
        }
    }

    /**
     * 验证apiKey是否过期
     *
     * @param apiKey
     * @return
     */
    private Pair<Boolean, String> validOpenApiKey(String apiKey) {
        if (this.apiKeyValid.test(apiKey)) {
            return Pair.of(true, "apiKey");
        }
        return Pair.of(false, null);
    }
}
