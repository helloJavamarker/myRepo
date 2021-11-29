package com.example.security.voter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author by mark
 * @Classname AllowHostVoter
 * @Description TODO
 * @Date 2021/11/18 1:32 下午
 */
@Slf4j
public class AllowHostVoter  implements AccessDecisionVoter<FilterInvocation> {
    private RequestMatcher allowRequest;
    private String masterHost;

    public AllowHostVoter(RequestMatcher allowRequest, String masterHost) {
        this.allowRequest = allowRequest;
        this.masterHost = masterHost;
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation filterInvocation, Collection<ConfigAttribute> collection) {
        HttpServletRequest request = filterInvocation.getRequest();
        if (this.allowRequest.matches(request)) {
            return ACCESS_GRANTED;
        }
        String host = request.getHeader("Host");
        int idx = host.indexOf(":");
        if (idx > -1) {
            host = host.substring(0, idx);
        }
        if (masterHost.equals(host)) {
            return ACCESS_GRANTED;
        }
        log.error("{} 的 {} 存在Host风险,禁止访问", request.getRequestURI(), host);
        throw new AccessDeniedException("Host头攻击风险");
    }
}
