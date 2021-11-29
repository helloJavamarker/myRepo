package com.example.security.util;

import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * @author by mark
 * @Classname AttrRequestMatcher
 * @Description TODO
 * @Date 2021/11/18 10:23 上午
 */
public class AttrRequestMatcher implements RequestMatcher {

    private final String expectedAttrName;
    private final String expectedAttrValue;

    public AttrRequestMatcher(String expectedAttrName) {
        this(expectedAttrName, null);
    }

    public AttrRequestMatcher(String expectedAttrName, String expectedAttrValue) {
        this.expectedAttrName = expectedAttrName;
        this.expectedAttrValue = expectedAttrValue;
    }


    @Override
    public boolean matches(HttpServletRequest httpServletRequest) {
        return false;
    }
}
