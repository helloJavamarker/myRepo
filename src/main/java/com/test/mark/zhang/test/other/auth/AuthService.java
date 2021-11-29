package com.test.mark.zhang.test.other.auth;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author by mark
 * @Classname AuthService
 * @Description TODO
 * @Date 2021/11/16 9:15 上午
 */
public interface AuthService {

    void reloadAuth(InputStream authConfig,String appName ) throws IOException;
}
