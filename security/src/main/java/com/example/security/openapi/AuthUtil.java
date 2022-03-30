package com.example.security.openapi;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author by mark
 * @Classname AuthUtil
 * @Description TODO
 * @Date 2022/3/29 4:22 下午
 */
public class AuthUtil {

    public static final String SECURITY_CONTEXT_KEY =  "SECURITY_CONTEXT_KEY";

    public static Authentication getSecurityContext(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        Object object = session.getAttribute(SECURITY_CONTEXT_KEY);
        if (!(object instanceof SecurityContext)) {
            return null;
        }
        return ((SecurityContext) object).getAuthentication();
    }
}
