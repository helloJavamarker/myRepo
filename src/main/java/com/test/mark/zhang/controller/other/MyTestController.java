package com.test.mark.zhang.controller.other;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MyTestController {

    @RequestMapping("hello")
    @ApiOperation("测试swagger")
    public String helloController(String name) {
        return "success::" + name;
    }

    //post请求也能使用requestParam
    @PostMapping("hello2")
    public String hello2(@RequestParam("content") String content, @RequestParam("content2") String content2) {
        return "success";
    }

    @GetMapping("getWithList")
    public String getWithList(@ModelAttribute RequestBean bean) {
        System.out.println(bean);
        return "success";
    }

    @GetMapping("getWithListTest")
    public String getWithListTest(@RequestParam RequestBean bean) {
        System.out.println(bean);
        return "success";
    }


    @GetMapping("getWithListTest2")
    public String getWithListTest2(@RequestParam("name") String name, @RequestParam("age") int age, @RequestParam("hobbys") List<String> hobbys) {
        System.out.println(name);
        System.out.println(age);
        System.out.println(hobbys);
        return "success";
    }

    @PostMapping("post")
    public String getWithPost(@RequestBody RequestBean bean) {
        System.out.println(bean);
        return "postBean";
    }

    @GetMapping("/getHttp")
    public void getHttp() throws IOException {
        //1.获得一个httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //2.生成一个get请求
        HttpGet httpget = new HttpGet("http://localhost/");
        //3.执行get请求并返回结果
        CloseableHttpResponse response = httpclient.execute(httpget);
        try {
            //4.处理结果
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                //内容写入文件
                System.out.println("内容长度：" + content.length());
                JSONObject jsonObject = JSONObject.parseObject(content);
                if (200 == jsonObject.getInteger("back_code")) {
//                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONArray data = jsonObject.getJSONArray("data");
                    JSONObject jsonObject1 = data.getJSONObject(0);

                }

            }
        } finally {
            response.close();
        }
    }

}
