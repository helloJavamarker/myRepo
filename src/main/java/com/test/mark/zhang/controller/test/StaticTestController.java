package com.test.mark.zhang.controller.test;

import com.alibaba.fastjson.JSON;
import com.test.mark.zhang.cache.caffeine.UserInfo;
import com.test.mark.zhang.entity.Person;
import com.test.mark.zhang.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticTestController {

  //    @Autowired
  //    private TestService testService;
  private static TestService testService;

  @Autowired
  private UserInfo userInfo;

  @GetMapping("/user/test")
  public UserInfo getUser() {
      userInfo.setAge(23);
      userInfo.setUserName("zhangsan");
      userInfo.setPassWord("password");
      return userInfo;
  }

    @Autowired
    public StaticTestController(TestService testService) {
        StaticTestController.testService = testService;
    }


    /**
     * java.lang.NullPointerException: null
     * 虽然注入的service编译不报错,但是运行会报错
     */
//    @Autowired
//    private static TestService testService;

    @GetMapping("/with/static")
    private void test() {
        testService.sout();
        System.out.println("hello");
    }

}
