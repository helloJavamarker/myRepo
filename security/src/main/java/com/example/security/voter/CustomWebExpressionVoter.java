package com.example.security.voter;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;

/**
 * @author by mark
 * @Classname CustomWebExpressionVoter
 * @Description TODO
 * @Date 2021/11/18 1:45 下午
 */
public class CustomWebExpressionVoter  implements AccessDecisionVoter<FilterInvocation> {
    //todo
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation filterInvocation, Collection<ConfigAttribute> collection) {
        return 0;
    }
}
