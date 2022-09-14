package com.test.mark.zhang.test.other.project.common;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.Proxy.Type;
import java.nio.charset.StandardCharsets;

public class HttpAndHttpsProxy {

    /**
     * url:外网测试地址 param：请求报文 proxy：代理地址（内网IP地址：10.0.100.00） port ：端口号（22）
     * **/
    public static String httpProxy(String url, String param, String proxy,
                                   int port) {
        HttpURLConnection httpConn = null;
//        HttpsURLConnection httpsConn = null;
        PrintWriter out = null;
        OutputStreamWriter out1 = null;
        BufferedReader in = null;
        String result = "";
        BufferedReader reader = null;
        try {
            URL urlClient = new URL(url);
            System.out.println("请求的URL========：" + urlClient);
            // 创建代理
            Proxy proxy1 = new Proxy(Type.HTTP, new InetSocketAddress(proxy,
                    port));
            // 设置代理
            httpConn = (HttpURLConnection) urlClient.openConnection(proxy1);
            // 设置通用的请求属性
            httpConn.setRequestProperty("accept", "*/*");
            httpConn.setRequestProperty("connection", "Keep-Alive");
            httpConn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);

            //java.net.SocketException: Unexpected end of file from server
            // 下面两个配置可以避免Unexpected end of file from server
            httpConn.setConnectTimeout(10000);
            httpConn.setReadTimeout(10000);


            // 获取URLConnection对象对应的输出流
            //使请求报文不中文乱码
            out1 = new OutputStreamWriter(httpConn.getOutputStream(), StandardCharsets.UTF_8);

            out1.write(param);

            // 发送请求参数
            // out.print(param);
            // flush输出流的缓冲
            out1.flush();
            // 定义BufferedReader输入流来读取URL的响应
            // in = new BufferedReader(
            // new InputStreamReader(httpConn.getInputStream()));

            //使返回的报文不中文乱码
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), StandardCharsets.UTF_8));

            String line;
            
            
            while ((line = in.readLine()) != null) {
                result += line;
            }
            // 断开连接
            httpConn.disconnect();
            System.out.println("====result====" + result);
            System.out.println("返回结果：" + httpConn.getResponseMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (out1 != null) {
                    out1.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // 示例
//        httpProxy("请求地址", "请求参数", "代理地址", 0);
        httpProxy("http://www.sogou.com", "", "120.220.220.95", 8085);
    }
}