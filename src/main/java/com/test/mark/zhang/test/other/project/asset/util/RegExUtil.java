package com.test.mark.zhang.test.other.project.asset.util;

import java.util.regex.Pattern;

/**
 * @author by mark
 * @Classname RegExUtil
 * @Description TODO
 * @Date 2022/7/8 2:50 下午
 */
public class RegExUtil {
    private static final String DOMAIN_REGEX = "";
    private static final String IP_REGEX = "";
    private static final String EMAIL_REGEX = "";
    private static final String PHONE_REGEX = "";
    private static final String MAC_REGEX = "^(?:[0-9A-F]{2}-){5}[0-9A-F]{2}$";

    public static boolean verifyDomain(String domain) {
        return Pattern.matches(DOMAIN_REGEX, domain);
    }

    public static boolean verifyIp(String ip) {
        return Pattern.matches(IP_REGEX, ip);
    }

    public static boolean verifyMac(String mac) {
        return Pattern.matches(MAC_REGEX, mac);
    }
}
