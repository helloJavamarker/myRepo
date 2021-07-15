package com.test.mark.zhang.test.other.project.cascade.session;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author by mark
 * @Classname OtherUtil
 * @Description TODO
 * @Date 2021/7/14 1:36 下午
 */
@Slf4j
public class OtherUtil {
    //私有构造
    private OtherUtil() {
    }

    //private static final Logger LOGGER = LoggerFactory.getLogger(OtherUtil.class);
    private static ThreadLocal<HttpServletRequest> reqLocal = new ThreadLocal<>();
    private static ThreadLocal<HttpServletResponse> resLocal = new ThreadLocal<>();

    public static HttpSession getSession() {
        HttpSession session = null;
        try {
            if (getRequest() == null) {
                return null;
            }
            session = getRequest().getSession();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return session;
    }

    public static HttpServletRequest getRequest() {
        return reqLocal.get();
    }

    public static ThreadLocal<HttpServletRequest> getReqLocal() {
        return reqLocal;
    }

    public static void setReqLocal(ThreadLocal<HttpServletRequest> reqLocal) {
        OtherUtil.reqLocal = reqLocal;
    }

    public static ThreadLocal<HttpServletResponse> getResLocal() {
        return resLocal;
    }

    public static void setResLocal(ThreadLocal<HttpServletResponse> resLocal) {
        OtherUtil.resLocal = resLocal;
    }

    public static User getUesr() {
        return getObject("userinfo");
    }

    private static <R> R getObject(String key) {
        R r = null;
        HttpSession session = getSession();
        if (session == null) {
            return r;
        }
        try {
            r = (R) session.getAttribute(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }
}
