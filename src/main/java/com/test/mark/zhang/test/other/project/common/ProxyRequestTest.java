package com.test.mark.zhang.test.other.project.common;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author by mark
 * @Classname ProxyRequestTest
 * @Description TODO
 * @Date 2022/6/28 10:33 上午
 */
public class ProxyRequestTest {

    //代理服务器是一个中间服务器,功能不止是请求转发,还能缓存数据,隐藏真实ip等
    //java有两种方式实现代理

    // 第一种:命令行
    //java -Dhttp.proxyHost=webcache.example.com -Dhttp.proxyPort=8080 -Dhttp.nonProxyHosts="localhost|host.example.com" test.jar

    //第二种

    /**
     * @link http://generalthink.github.io/2020/02/20/use-proxy-in-java/
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//        通过System.setProperty (String，String)
        // 设置代理
        System.setProperty("http.proxyHost", "webcache.example.com");
        System.setProperty("http.proxyPort", "8080");

        // 下一个连接将会使用代理
        URL url = new URL("http://java.example.org/");
        InputStream in = url.openStream();

        // 清除代理
        System.clearProperty("http.proxyHost");
        // 从现在开始，http连接将直接完成而不再使用代理

        //说明
//        http.proxyHost : 代理服务器主机名
//        http.proxyPort : 端口号，默认是 80
//        https.proxyHost : https 代理服务器主机名
//        https.proxyPort: 代理端口号，默认是 443
//        http.nonProxyHosts : 指定绕过代理的主机列表，使用 | 分割的模式列表，可以以通配符 * 开头或者结尾，任何匹配这些模式之一的主机都将通过直接连接而不是通过代理访问。该设置对 http,https 通用

    }
}
