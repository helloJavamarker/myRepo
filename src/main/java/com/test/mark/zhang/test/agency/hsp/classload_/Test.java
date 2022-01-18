package com.test.mark.zhang.test.agency.hsp.classload_;

import com.github.kevinsawicki.http.HttpRequest;
import com.test.mark.zhang.test.agency.hsp.Cat;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class Test {
    public static void main(String[] args) {
        Cat cat = new Cat();
        //HttpRequest.post("").body("").execute();  //hutool

        //1、发送一个get请求，获取响应码，够方便了吧。
        int code = HttpRequest.get("http://baidu.com").code();
        //2、加个请求参数呢？可以直接加在get方法里，选择是否进行编码，不习惯的还可以用Map传参哦。
        HttpRequest request = HttpRequest.get("http://google.com", true, 'q', "baseball gloves", "size", 100);
    }

}
