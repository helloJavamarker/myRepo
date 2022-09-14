package com.test.mark.zhang.test.other.project.common;

import com.mashape.unirest.http.Headers;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.entity.ContentType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.concurrent.Future;

/**
 * @author by mark
 * @Classname UnirestUtil
 * @Description 轻量级http开发库Unirest
 * @Date 2022/6/29 3:59 下午
 */
public class UnirestUtil {

    private void postDemo() throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.post("http://httpbin.org/post")
                .header("accept", "application/json")
                .queryString("apiKey", "123")
                .field("parameter", "value")
                .field("foo", "bar")
                .asJson();
        //当执行了asType方法之后，Requests就被生成了，可以转换的内容为Json, Binary, String, Object
        //.body(String|JsonNode|Object)，当使用.body(Object) 时需要额外的配置参数
        //.fields(Map<String, Object> fields)会把每个k-v填入form表单
        //.headers(Map<String, String> headers)填入headers参数
    }

    private void getDemo() throws UnirestException {
        Unirest.get("http://httpbin.org/{method}")
                .routeParam("method", "get")
                .queryString("name", "Mark")
                .asJson();

        //这里的占位符会被get代替
    }

    private void asyncDemo() {
        Future<HttpResponse<JsonNode>> future = Unirest.post("http://httpbin.org/post")
                .header("accept", "application/json")
                .field("param1", "value1")
                .field("param2", "value2")
                .asJsonAsync(new Callback<JsonNode>() {
                    @Override
                    public void failed(UnirestException e) {
                        System.out.println("The request has failed");
                    }

                    @Override
                    public void completed(HttpResponse<JsonNode> response) {
                        int code = response.getStatus();
                        Headers headers = response.getHeaders();
                        JsonNode body = response.getBody();
                        InputStream rawBody = response.getRawBody();
                    }

                    @Override
                    public void cancelled() {
                        System.out.println("The request has been cancelled");
                    }
                });
    }

    private void fileDemo() throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.post("http://httpbin.org/post")
                .header("accept", "application/json")
                .field("parameter", "value")
                .field("file", new File("/tmp/file"))
                .asJson();
    }

    /**
     * 定制body
     *
     * @throws UnirestException
     */
    private void bodyDemo() throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.post("http://httpbin.org/post")
                .header("accept", "application/json")
                .body("{\"parameter\":\"value\", \"foo\":\"bar\"}")
                .asJson();
    }

    /**
     * 定制比特流
     *
     * @throws IOException
     * @throws UnirestException
     */
    private void byteDemo() throws IOException, UnirestException, URISyntaxException {
        final InputStream stream = new FileInputStream(new File(getClass().getResource("/image.jpg").toURI()));
        final byte[] bytes = new byte[stream.available()];
        stream.read(bytes);
        stream.close();
        final HttpResponse<JsonNode> jsonResponse = Unirest.post("http://httpbin.org/post")
                .field("name", "Mark")
                .field("file", bytes, "image.jpg")
                .asJson();

        HttpResponse<JsonNode> jsonResponse2 = Unirest.post("http://httpbin.org/post")
                .field("name", "Mark")
                .field("file", new FileInputStream(new File(getClass().getResource("/image.jpg").toURI())), ContentType.APPLICATION_OCTET_STREAM, "image.jpg")
                .asJson();
    }

    private void basicAuth() throws UnirestException {
        HttpResponse<JsonNode> response =
                Unirest.get("http://httpbin.org/headers").basicAuth("username", "password").asJson();
    }

    private void respAndReq() {
//        Java版本的是构造者模式,通过下面这些方式获取HttpRequest

//        GetRequest request = Unirest.get(String url);
//        GetRequest request = Unirest.head(String url);
//        HttpRequestWithBody request = Unirest.post(String url);
//        HttpRequestWithBody request = Unirest.put(String url);
//        HttpRequestWithBody request = Unirest.patch(String url);
//        HttpRequestWithBody request = Unirest.options(String url);
//        HttpRequestWithBody request = Unirest.delete(String url);
//        收到反馈时，返回一个Object，object对于不同的语言应该有相同的keys
//
//                .getStatus() - HTTP Response Status Code (Example: 200)
//.getStatusText() - HTTP Response Status Text (Example: "OK")
//.getHeaders() - HTTP Response Headers
//.getBody() - Parsed response body where applicable, for example JSON responses are parsed to Objects / Associative Arrays.
//.getRawBody() - Un-parsed response body
    }

    private void highLevelDemo() {
//        可以设置一些高级的配置，调整Unirest
//        定制HTTP clients,可以修改HttpClient和HttpAsyncClient的实现，然后设置进Unirest中
//
//        Unirest.setHttpClient(httpClient);
//        Unirest.setAsyncHttpClient(asyncHttpClient);
//        Timeouts以ms为单位
//        默认连接超时10000，socket超时60000，设置为0为关闭
//
//        Unirest.setTimeouts(long connectionTimeout, long socketTimeout);
//        默认的Request Headers,每个request都会发出去
//
//        Unirest.setDefaultHeader("Header1", "Value1");
//        Unirest.setDefaultHeader("Header2", "Value2");
//        清除默认的头
//
//        Unirest.clearDefaultHeaders();
//        并发,可以设置并发级别，通过设置同步或者异步的client
//        默认pool中全部的连接限制，maxTotal设置为200，并且maxPerRoute目标host设置为20
//
//        Unirest.setConcurrency(int maxTotal, int maxPerRoute);
//        代理
//
//        Unirest.setProxy(new HttpHost("127.0.0.1", 8000));
//        退出应用
//        Unirest开启后台一个event loop,需要手动调用执行退出
//
//        Unirest.shutdown();
    }
}
