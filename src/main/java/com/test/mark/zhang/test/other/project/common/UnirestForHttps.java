package com.test.mark.zhang.test.other.project.common;

import com.google.common.collect.ImmutableMap;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * @author by mark
 * @Classname UnirestForHttps
 * @Description TODO
 * @Date 2022/6/29 4:38 下午
 */
@Slf4j
public class UnirestForHttps {
    private UnirestForHttps() {
    }

    public static void main(String[] args) throws Exception {
        String res = "";

        res = Unirest.post("http://172.25.xx.xx:12345/dolphinscheduler/login")
                .header("accept", "application/json")
                .field("userName", "admin")
                .field("userPassword", "dolphinscheduler123")
                .asString()
                .getBody();

        res = Unirest.get("http://172.25.xx.xx:12345/dolphinscheduler/log/detail")
                .header("accept", "application/json")
                .header("token", "ca3e91749eee187fa9a797d92cf5cb6d")
                .queryString("taskInstanceId", "703")
                .queryString("skipLineNum", "0")
                .queryString("limit", "1000")
                .asString()
                .getBody();
    }

    private static SSLContext sslContext;

    static {
        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) {
                    return true;
                }
            }).build();

//            Unirest.setObjectMapper(new ObjectMapper() {
//                @Override
//                public <T> T readValue(String s, Class<T> aClass) {
//                    return null;
//                }
//
//                @Override
//                public String writeValue(Object o) {
//                    return null;
//                }
//            });
        } catch (Exception e) {
            log.error("init error");
        }
    }


    public static CloseableHttpClient getClient() {
        int timeout = 1000;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout).build();
        return HttpClients.custom().setSSLContext(sslContext)
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .setDefaultRequestConfig(config).build();
    }

    public static String get(String url, Map<String, String> headers) {
        try (CloseableHttpClient client = getClient()) {
            Unirest.setHttpClient(client);
            return Unirest.get(url).headers(headers).asString().getBody();
        } catch (IOException | UnirestException e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    public static String post(String url, Map<String, String> headers) {
        try (CloseableHttpClient client = getClient()) {
            Unirest.setHttpClient(client);
            return Unirest.post(url).headers(headers).asString().getBody();
        } catch (IOException | UnirestException e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    public static String post(String url, String body, Map<String, String> headers) {
        try (CloseableHttpClient client = getClient()) {
            Unirest.setHttpClient(client);
            return Unirest.post(url).headers(headers).body(body).asString().getBody();
        } catch (IOException | UnirestException e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    public static String put(String url, Map<String, String> headers) {
        try (CloseableHttpClient client = getClient()) {
            Unirest.setHttpClient(client);
            return Unirest.put(url).headers(headers).asString().getBody();
        } catch (IOException | UnirestException e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    public static String delete(String url, Map<String, String> headers) {
        try (CloseableHttpClient client = getClient()) {
            Unirest.setHttpClient(client);
            return Unirest.delete(url).headers(headers).asString().getBody();
        } catch (IOException | UnirestException e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    public static String postFile(String url, String body, String name, String fileName) {
        try (CloseableHttpClient client = getClient()) {
            Unirest.setHttpClient(client);
            return Unirest.post(url)
                    .fields(ImmutableMap.of()).field(name, (body + "\r\n").getBytes(), ContentType.create("text/xml", "utf-8"), fileName)
                    .asString().getBody();
        } catch (IOException | UnirestException e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

}
