package com.example.security.voter;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author by mark
 * @Classname AllowRefererVoter
 * @Description 校验referer,防止CSRF风险
 * @Date 2021/11/18 11:01 上午
 */
@Slf4j
public class AllowRefererVoter implements AccessDecisionVoter<FilterInvocation> {

    private RequestMatcher allowRequest;
    private String masterHost;

    public AllowRefererVoter(String masterHost, RequestMatcher allowRequest) {
        String[] allowUrl = {"/api/oauth/**", "/oauth/**"};
        List<RequestMatcher> matchers = Arrays.asList(allowUrl).stream().map(AntPathRequestMatcher::new).collect(Collectors.toList());
        matchers.add(allowRequest);
        this.allowRequest = new OrRequestMatcher(matchers);
        this.masterHost = masterHost;
    }

    /**
     * 两个 supports 方法用来判断投票器是否支持当前请求。
     * @param configAttribute
     * @return
     */
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    /**
     * int ACCESS_GRANTED = 1;   赞成
     *     int ACCESS_ABSTAIN = 0; 弃权
     *     int ACCESS_DENIED = -1; 拒绝
     * @param authentication
     * @param filterInvocation
     * @param collection
     * @return
     */
    @SneakyThrows
    @Override
    public int vote(Authentication authentication, FilterInvocation filterInvocation, Collection<ConfigAttribute> collection) {
        HttpServletRequest request = filterInvocation.getRequest();
        if (allowRequest.matches(request)) {
            return ACCESS_GRANTED;
        }
        String referer = request.getHeader("Referer");
        if (StringUtils.isEmpty(referer)) {
            log.error("{} 的 {} 存在CSRF风险,禁止访问", request.getRequestURI(), referer);
            throw new AccessDeniedException("CSRF风险");
        }
        try {
            URL url = new URL(referer);
            String host = url.getHost();
            if (masterHost.equals(host)) {
                return ACCESS_GRANTED;
            }
        } catch (MalformedURLException e) {
            log.error("referer 格式不正确");
            throw new AccessDeniedException("CSRF风险");
        }
        throw new AccessDeniedException("CSRF风险");
    }
}
