package com.test.mark.zhang.test.other.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.lang.reflect.Method;

/**
 * @author by mark
 * @Classname WebServiceTest
 * @Description TODO
 * @Date 2021/12/27 10:13 上午
 */
@WebService(serviceName = "webServiceTest", targetNamespace = "Method")
public class WebServiceTest {

    private WebInterface webInterface;

    @WebMethod(operationName = "Method")
    @WebResult(name = "result")
    public String method(@WebParam(name = "name") String name) {
        webInterface.sayHi(name);
        return "webservice:" + name;
    }

    public static void main(String[] args) throws Exception{
        Class clazz = Class.forName("com.test.mark.zhang.test.other.webservice.WebServiceTest");
        WebServiceTest webServiceTest = (WebServiceTest) clazz.newInstance();
        Method method = clazz.getMethod("print");
        method.invoke(webServiceTest, null);
    }

    public void print() {
        System.out.println("hello");
    }
}
