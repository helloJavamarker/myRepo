package com.example.security.openapi;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @author by mark
 * @Classname SecretUtil
 * @Description TODO
 * @Date 2022/3/29 4:35 下午
 */
public class SecretUtil {
    public static final String HMAC_SHA1 = "HmaSha1";

    public static String jasyptDecrypt(String content, String key) {
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);
        return basicTextEncryptor.encrypt(content);
    }
}
